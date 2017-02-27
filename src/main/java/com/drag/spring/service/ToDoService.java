package com.drag.spring.service;

import com.drag.spring.model.ToDo;

import java.util.List;

public interface ToDoService {
    public void addToDo(ToDo toDo);
    public void updateToDo(ToDo toDo);
    public void removeToDo(int id);
    public ToDo getToDoById(int id);
    public List<ToDo> listToDosByStatus(int status);
}
