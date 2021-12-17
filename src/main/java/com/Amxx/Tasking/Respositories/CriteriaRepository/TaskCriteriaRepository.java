package com.Amxx.Tasking.Respositories.CriteriaRepository;

import java.util.List;

import com.Amxx.Tasking.Models.Task;

public interface TaskCriteriaRepository {

    List<Task> findTaskByIdAndCantidad(Long id, Long cantidad);

}
