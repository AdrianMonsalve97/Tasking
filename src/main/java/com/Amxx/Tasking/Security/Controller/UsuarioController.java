package com.Amxx.Tasking.Security.Controller;

import java.util.ArrayList;
import java.util.List;

import com.Amxx.Tasking.Dto.Mensaje;
import com.Amxx.Tasking.Dto.UsuarioDto;
import com.Amxx.Tasking.Security.Models.Usuario;
import com.Amxx.Tasking.Service.TaskService;
import com.Amxx.Tasking.Service.UsuarioService;

import org.modelmapper.ModelMapper;
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
    public ResponseEntity<List<UsuarioDto>> list() {
        List<Usuario> list = usuarioService.list();
        ModelMapper modelMapper = new ModelMapper();
        List<UsuarioDto> res = new ArrayList<>();
        for (Usuario usuario : list) {
            UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
            res.add(usuarioDto);
        }
        return new ResponseEntity<List<UsuarioDto>>(res, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Long id) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    private Usuario save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return usuario;
    }





}
