package com.example.todolist.controller;

import com.example.todolist.Domain.Todo;
import com.example.todolist.Service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }
    @GetMapping("/todos")
    @ResponseBody
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("/todos")
    @ResponseBody
    public List<Todo> createTodos(@RequestBody Todo todo){
        todoService.createTodo(todo);
        return todoService.getTodos();
    }
}
