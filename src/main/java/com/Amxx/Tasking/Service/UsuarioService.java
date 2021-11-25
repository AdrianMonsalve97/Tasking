package com.Amxx.Tasking.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.Amxx.Tasking.Models.Usuario;

public interface UsuarioService {

    void save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    Page<Usuario> getUsuario(int page, int size, Sort sort);

    List<Usuario> list();

}
