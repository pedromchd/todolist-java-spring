package br.com.pedromchd.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedromchd.todolist.dto.TaskDTO;
import br.com.pedromchd.todolist.model.Task;
import br.com.pedromchd.todolist.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskService.getAll();
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id) {
        Task task = taskService.getById(id);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.save(taskDTO);
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
        Task task = taskService.update(id, taskDTO);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        Boolean deleted = taskService.delete(id);
        if (deleted) {
            return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(deleted, HttpStatus.NOT_FOUND);
        }
    }

}
