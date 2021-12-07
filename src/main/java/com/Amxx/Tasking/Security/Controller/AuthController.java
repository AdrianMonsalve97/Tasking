package com.Amxx.Tasking.Security.Controller;

import com.Amxx.Tasking.Dto.Mensaje;
import com.Amxx.Tasking.Security.Dto.JwtDto;
import com.Amxx.Tasking.Security.Dto.LoginUsuario;
import com.Amxx.Tasking.Security.Dto.NuevoUsuario;
import com.Amxx.Tasking.Security.Enums.RolNombre;
import com.Amxx.Tasking.Security.Jwt.JwtProvider;
import com.Amxx.Tasking.Security.Models.Rol;
import com.Amxx.Tasking.Security.Models.Usuario;
import com.Amxx.Tasking.Security.service.RolService;
import com.Amxx.Tasking.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o usuario incorrecto"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNickname(nuevoUsuario.getNickname()))
            return new ResponseEntity(new Mensaje("usuario ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombre(nuevoUsuario.getNombre()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNickname(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNickname(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
