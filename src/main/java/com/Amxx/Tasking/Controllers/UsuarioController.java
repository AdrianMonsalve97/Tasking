package com.Amxx.Tasking.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Models.Usuario;
import com.Amxx.Tasking.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> list = usuarioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    private Usuario save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return usuario;
    }
   

}
