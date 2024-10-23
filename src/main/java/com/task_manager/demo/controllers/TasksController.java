package com.task_manager.demo.controllers;


import com.task_manager.demo.contantes.TaskStatus;
import com.task_manager.demo.models.TaskDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.task_manager.demo.services.TaskService;

import java.util.List;

@RestController()
@RequestMapping("/tasks")
@AllArgsConstructor
@CrossOrigin
public class TasksController {

    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(required = false) TaskStatus taskStatus) throws Exception {
        if(taskStatus == null) {
            return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
        }
        return new ResponseEntity<>(taskService.getTaskByStatus(taskStatus), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskDTO) throws Exception {
        return new ResponseEntity<>(taskService.createTask(taskDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String id, @RequestBody @Valid TaskDTO taskDTO) throws Exception {
        return new ResponseEntity<>(taskService.updateTask(id, taskDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) throws Exception {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }



}
