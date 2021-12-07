package com.Amxx.Tasking.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.Amxx.Tasking.Dto.Mensaje;
import com.Amxx.Tasking.Dto.TaskDto;
import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Service.TaskService;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/LIst")
    public List<TaskDto> list() {
        List<Task> tasks = taskService.list();
        ModelMapper modelMapper = new ModelMapper();
        List<TaskDto> res = new ArrayList<>();
        for (Task task : tasks) {
            res.add(modelMapper.map(task, TaskDto.class));
            // res.add(mapper.map(cliente, ClienteDto.class));
        }

        return res;
    }

    @PostMapping("/addTask")
    private Task save(@RequestBody Task task) {
        taskService.save(task);
        return task;
    }

    @PostMapping(value = "/agregarTask/{id}")

    public ResponseEntity<?> create(@RequestBody TaskDto taskDto) {
        if (StringUtils.isBlank(taskDto.getDescription()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        else if (taskDto.getId() == null || taskDto.getId() < 0)
            return new ResponseEntity(new Mensaje("el id de la tarea debe ser mayor a 1"), HttpStatus.BAD_REQUEST);
        if (taskService.existsByDescription(taskDto.getDescription()))
            return new ResponseEntity(new Mensaje("Tarea ya existe"), HttpStatus.BAD_REQUEST);
        Task task = new Task(taskDto.getDescription(), taskDto.getId(), taskDto.getFecha(), taskDto.getUsuario(),
                taskDto.getCantidad());
        taskService.save(task);
        return new ResponseEntity(new Mensaje("tarea creada"), HttpStatus.OK);
    }

}
