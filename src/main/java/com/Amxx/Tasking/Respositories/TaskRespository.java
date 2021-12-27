package com.Amxx.Tasking.Respositories;

import java.util.Optional;

import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Security.Models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRespository extends JpaRepository<Task, Long> {

    // query que haga una validacion si el estado es urgente tiene que traer las
    // tareas urgentes

    boolean existsByDescription(String description);

    boolean existsById(Long id);

    Task findFirstById(Long id);

    Optional<Usuario> save(Usuario usuario);

    

}
