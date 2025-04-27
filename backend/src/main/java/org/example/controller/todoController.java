package org.example.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.example.Service.userService;
import org.example.model.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;



@RestController
@RequestMapping("/api")
@CrossOrigin
public class todoController
{
    @Autowired
    private userService service;

    @GetMapping
    public List<TODO> getAllTodos()
    {
        List<TODO> todos = service.getAllTodos();
//        System.out.println(todos.get(0).getId());
        return todos;
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
        todo.setUpdated_on(LocalDateTime.now());
        service.createTodo(todo);
        return ResponseEntity.ok(todo);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<TODO> updateTodo(@PathVariable Long id, @RequestBody Map<String,Object> updates)
    {
        TODO todo = service.getTodoById(id).orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        boolean old_val = (boolean) updates.get("status");
        todo.setStatus(old_val);
        service.createTodo(todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id)
    {
        service.deleteTodo(id);
    }
}
