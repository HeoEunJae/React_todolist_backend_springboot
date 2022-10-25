package com.example.todolist.controller;

import com.example.todolist.Domain.Todo;
import com.example.todolist.Service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    @ResponseBody
    public Todo createTodos(@RequestBody Todo todo){

        return todoService.createTodo(todo);
    }
}
