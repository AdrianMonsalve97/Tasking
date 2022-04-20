package com.Amxx.Tasking.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.Amxx.Tasking.Dto.Mensaje;
import com.Amxx.Tasking.Dto.TaskDto;
import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Service.TaskService;
import com.Amxx.Tasking.Service.UsuarioService;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired

    UsuarioService usuarioService;

    @GetMapping("/List")
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
    private Task saveTask(@RequestBody Task task) {
        taskService.saveTask(task);
        return task;
    }

    @PostMapping(value = "/agregarTask/{id}")

    public ResponseEntity<?> create(@RequestBody TaskDto taskDto) {
        if (StringUtils.isBlank(taskDto.getDescription()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"),
                    HttpStatus.BAD_REQUEST);
        else if (taskDto.getId() == null || taskDto.getId() < 0)
            return new ResponseEntity(new Mensaje("el id de la tarea debe ser mayor a 1"), HttpStatus.BAD_REQUEST);
        if (taskService.existsByDescription(taskDto.getDescription()))
            return new ResponseEntity(new Mensaje("Tarea ya existe"),
                    HttpStatus.BAD_REQUEST);
        Task task = new Task(taskDto.getDescription(), taskDto.getId(),
                taskDto.getFecha(), taskDto.getUsuario(),
                taskDto.getCantidad());
        return new ResponseEntity(new Mensaje("tarea creada"), HttpStatus.OK);
    }

    // @PostMapping("/asignarTarea/{id}")
    // public ResponseEntity<Task> save(@RequestBody Task task) {
    //     return new ResponseEntity<Task>(this.taskService.save(task), HttpStatus.CREATED);
    // }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!taskService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        taskService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

    // consulta de criteria //

    @GetMapping("/crit")
    public ResponseEntity<List<TaskDto>> Crit(@RequestParam(required = false) Long id,
            @RequestParam(required = false) Long cantidad) {
        List<Task> tasks = taskService.findTaskByIdAndCantidad(id, cantidad);
        ModelMapper modelMapper = new ModelMapper();
        List<TaskDto> A = new ArrayList<>();
        for (Task task : tasks) {
            A.add(modelMapper.map(task, TaskDto.class));
            // res.add(mapper.map(cliente, ClienteDto.class));
        }

        return new ResponseEntity<List<TaskDto>>(A, HttpStatus.OK);

    }

    // @GetMapping("/consultacriteria")
    // public List<TaskDto> findAllTasks(@RequestParam(required = false) Long id,
    // @RequestParam(required = false) Long cantidad) {
    // return taskService.findTaskByIdAndCantidad(id, cantidad);
    // }

}
