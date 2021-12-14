package com.Amxx.Tasking.Respositories;



import com.Amxx.Tasking.Models.Task;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRespository extends JpaRepository<Task, Long> {

    // query que haga una validacion si el estado es urgente tiene que traer las
    // tareas urgentes

    boolean existsByDescription(String description);
    boolean existsById(Long id);
    Task findFirstById(Long id);



 




}
