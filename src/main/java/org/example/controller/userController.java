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

    @PostMapping("/test")
    public ResponseEntity test(@RequestParam("file")MultipartFile file, HttpServletRequest request, OutputStream outputStream)
    {
        try(OutputStream os = new FileOutputStream("photo.txt")){

            os.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/csrf-token")
    public CsrfToken get_csrfToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
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
