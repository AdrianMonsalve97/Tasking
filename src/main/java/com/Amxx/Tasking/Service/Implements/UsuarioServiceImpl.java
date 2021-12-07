package com.Amxx.Tasking.Service.Implements;

import java.util.List;
import java.util.Optional;

import com.Amxx.Tasking.Respositories.UsuarioRepository;
import com.Amxx.Tasking.Security.Models.Usuario;
import com.Amxx.Tasking.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired

    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);

    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Page<Usuario> getUsuario(int page, int size, Sort sort) {
        return usuarioRepository.findAll(PageRequest.of(page, size, sort));
    }

    public Optional<Usuario> getOne(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return usuarioRepository.existsByNombre(nombre);
    }

    @Override
    public Optional<Usuario> getByNickname(String nickname) {
        return usuarioRepository.getByNickname(nickname);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return usuarioRepository.existsByNickname(nickname);
    }

}
