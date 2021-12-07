package com.Amxx.Tasking.Service;

import java.util.List;
import java.util.Optional;

import com.Amxx.Tasking.Security.Models.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UsuarioService {

    void save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    Page<Usuario> getUsuario(int page, int size, Sort sort);

    List<Usuario> list();

    boolean existsById(Long id);

    boolean existsByNombre(String nombre);
    boolean existsByNickname(String nickname);

    Optional<Usuario>  getOne(Long id);
    Optional<Usuario> getByNickname(String nickname);



}
