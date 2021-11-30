package com.Amxx.Tasking.Security.Controller;

import java.util.List;

import com.Amxx.Tasking.Dto.Mensaje;
import com.Amxx.Tasking.Dto.UsuarioDto;
import com.Amxx.Tasking.Models.Usuario;
import com.Amxx.Tasking.Service.TaskService;
import com.Amxx.Tasking.Service.UsuarioService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TaskService taskService;

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> list = usuarioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Long id) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }


   
    @PostMapping(value = "/agregar/{id}")

    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
        if(StringUtils.isBlank(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      else if(usuarioDto.getId()==null || usuarioDto.getId()<0 )
            return new ResponseEntity(new Mensaje("el id del usuario debe ser mayor que 1"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombre(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario = new Usuario(usuarioDto.getNombre(), usuarioDto.getId());
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }



    @PostMapping("/addUser")
    private Usuario save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return usuario;
    }





}
