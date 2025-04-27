package org.example.Service;

import org.example.model.TODO;
import org.example.repositories.todoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService
{
    @Autowired
    private todoRepo repo;

    public List<TODO> getAllTodos()
    {
        return repo.findAll();
    }

    public Optional<TODO> getTodoById(Long id)
    {
        return repo.findById(id);
    }

    public TODO createTodo(TODO todo)
    {
        return repo.save(todo);
    }

    public void deleteTodo(Long id)
    {
        repo.deleteById(id);
    }




}
