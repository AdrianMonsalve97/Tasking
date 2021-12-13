package com.Amxx.Tasking.Service.Implements;

import java.util.List;
import java.util.Optional;

import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Respositories.TaskRespository;
import com.Amxx.Tasking.Service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired

    TaskRespository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);

    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Page<Task> getTask(int page, int size, Sort sort) {
        return taskRepository.findAll(PageRequest.of(page, size, sort));
    }

    @Override
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @Override
    public boolean existsByDescription(String description) {

        return taskRepository.existsByDescription(description);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);

    }

    @Override
    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    @Override
    public Task findFirstById(Long id) {
        return taskRepository.findFirstById(id);
    }

}
