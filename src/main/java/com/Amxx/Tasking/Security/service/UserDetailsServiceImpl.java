package com.Amxx.Tasking.Security.service;

import com.Amxx.Tasking.Security.Models.Usuario;
import com.Amxx.Tasking.Security.Models.UsuarioPrincipal;
import com.Amxx.Tasking.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByNickname(nickname).get();
        return UsuarioPrincipal.build(usuario);
    }



 
}