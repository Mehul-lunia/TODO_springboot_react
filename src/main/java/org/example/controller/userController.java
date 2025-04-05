package org.example.controller;


import org.example.Service.userService;
import org.example.model.TODO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping("/api")
@CrossOrigin
public class userController
{
    @Autowired
    private userService service;

    @GetMapping
    public List<TODO> getAllTodos()
    {
        return service.getAllTodos();
    }

    @GetMapping("/test")
    public TODO test()
    {
        return new TODO("Practice SpringBoot","Understand IOC(Inversion of Control) and Dependency Injection.", LocalDateTime.now());
    }

    @GetMapping("/{id}")
    public Optional<TODO> getTodoById(@PathVariable Long id)
    {
        return service.getTodoById(id);
    }
    @PostMapping
    public ResponseEntity<TODO> createTodo(@RequestBody TODO todo)
    {
        todo.setCreated_on(LocalDateTime.now());
        service.createTodo(todo);
        return ResponseEntity.ok(todo);

    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id)
    {
        service.deleteTodo(id);
    }
}
