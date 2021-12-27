package com.Amxx.Tasking.Security.Controller;

import java.util.ArrayList;
import java.util.List;

import com.Amxx.Tasking.Dto.Mensaje;
import com.Amxx.Tasking.Dto.TaskDto;
import com.Amxx.Tasking.Dto.UsuarioDto;
import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Security.Models.Usuario;
import com.Amxx.Tasking.Service.TaskService;
import com.Amxx.Tasking.Service.UsuarioService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // @GetMapping("/lista")
    // public ResponseEntity<List<TaskDto>> list() {
    // List<Task> list = taskService.list();
    // ModelMapper modelMapper = new ModelMapper();
    // List<TaskDto> res = new ArrayList<>();
    // for (Task task : list) {
    // Task task = modelMapper.map(task, Task.class);
    // res.add(task);
    // }
    // return new ResponseEntity<List<Task>>(res, HttpStatus.OK);
    // }

    @GetMapping("lista")
    public ResponseEntity<List<TaskDto>> list() {
        List<Task> list = taskService.list();
        ModelMapper modelMapper = new ModelMapper();
        List<TaskDto> res = new ArrayList<>();
        for (Task task : list) {
            TaskDto taskDto = modelMapper.map(task, TaskDto.class);
            res.add(taskDto);
        }
        return new ResponseEntity<List<TaskDto>>(res, HttpStatus.OK);

    }

    // @GetMapping("/lista")
    // public ResponseEntity<List<Usuario>> list() {
    // List<Usuario> list = usuarioService.list();
    // return new ResponseEntity(list, HttpStatus.OK);
    // }

    @GetMapping("/detail/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable("id") Long id) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    // @PostMapping("/addUser")
    // private Usuario save(@RequestBody Usuario usuario) {
    // usuarioService.save(usuario);
    // return usuario;
    // }

    @PostMapping("/clientesav")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(this.usuarioService.save(usuario), HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    private Usuario update(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return usuario;
    }

}
