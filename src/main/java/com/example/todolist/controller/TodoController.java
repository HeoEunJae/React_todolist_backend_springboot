package com.example.todolist.controller;

import com.example.todolist.Domain.Todo;
import com.example.todolist.Service.TodoService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }
    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("/todos")
    public List<Todo> createTodos(@RequestBody Todo todo){
        todoService.createTodo(todo);
        return todoService.getTodos();
    }

    @DeleteMapping("/todos/{id}")
    public List<Todo> deleteTodos(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        todoService.deleteTodo(id, response);
        return todoService.getTodos();
    }
}
