package com.Amxx.Tasking.Service;

import java.util.List;
import java.util.Optional;

import com.Amxx.Tasking.Models.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface TaskService {

    Task save(Task task);

    Page<Task> getTask(int page, int size, Sort sort);

    List<Task> list();

    Optional<Task> findById(Long id);

    boolean existsByDescription(String description);

    void delete(Long id);

    boolean existsById(Long id);

    Task findFirstById(Long id);

    List<Task> findTaskByIdAndCantidad(Long id, Long cantidad);

    void saveTask(Task task);
}
