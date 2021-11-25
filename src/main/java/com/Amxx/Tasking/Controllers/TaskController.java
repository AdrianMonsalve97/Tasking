package com.Amxx.Tasking.Controllers;

import java.util.List;

import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Service.TaskService;

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

    @GetMapping("/lista")
    public ResponseEntity<List<Task>> list() {
        List<Task> list = taskService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/addTask")
    private Task save(@RequestBody Task task) {
        taskService.save(task);
        return task;
    }
    

}
