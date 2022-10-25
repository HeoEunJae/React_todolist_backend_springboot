package com.example.todolist.Service;

import com.example.todolist.Domain.Todo;
import com.example.todolist.dao.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){
        Todo newtodo = new Todo();
        newtodo.setContent(todo.getContent());
        newtodo.setChecked(false);
        todoRepository.save(newtodo);
        return newtodo;
    }
}
