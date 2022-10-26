package com.example.todolist.controller;

import com.example.todolist.Domain.Todo;
import com.example.todolist.Service.TodoService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }
    @GetMapping("")
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("")
    public List<Todo> createTodos(@RequestBody Todo todo){
        todoService.createTodo(todo);
        return todoService.getTodos();
    }

    @DeleteMapping("{id}")
    public List<Todo> deleteTodos(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        todoService.deleteTodo(id, response);
        return todoService.getTodos();
    }

    @PatchMapping("{id}")
    public List<Todo> checkedTodos(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        todoService.checkedTodo(id, response);
        return todoService.getTodos();
    }
}
