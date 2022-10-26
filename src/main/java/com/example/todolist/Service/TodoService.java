package com.example.todolist.Service;

import com.example.todolist.Domain.Todo;
import com.example.todolist.dao.TodoRepository;
import com.example.todolist.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(Integer id, HttpServletResponse response) throws IOException {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()){
            todoRepository.deleteById(id);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode("f-1");
            responseDTO.setMessage("이미 삭제된 할 일입니다.");
            response.setStatus(404);
            response.setHeader("content-type", "application/json;charset-uft-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), responseDTO);

        }
    }

    public void checkedTodo(Integer id, HttpServletResponse response) throws IOException {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()){
            Todo targettodo = todo.get();
            targettodo.setChecked(!targettodo.isChecked());
            todoRepository.save(targettodo);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode("f-2");
            responseDTO.setMessage("없는 할 일입니다.");
            response.setStatus(404);
            response.setHeader("content-type", "application/json;charset-uft-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), responseDTO);
        }
    }

    public void editTodoById(Integer id, Object content, HttpServletResponse response) throws IOException {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()){
            Todo targettodo = todo.get();
            targettodo.setContent((String)content);
            todoRepository.save(targettodo);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode("f-2");
            responseDTO.setMessage("없는 할 일입니다.");
            response.setStatus(404);
            response.setHeader("content-type", "application/json;charset-uft-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), responseDTO);
        }
    }
}
