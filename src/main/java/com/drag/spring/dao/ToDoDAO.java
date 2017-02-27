package com.drag.spring.dao;

import com.drag.spring.model.ToDo;

import java.util.List;

public interface ToDoDAO {
    public void addToDo(ToDo toDo);
    public void updateToDo(ToDo toDo);
    public void removeToDo(int id);
    public ToDo getToDoById(int id);
    public List<ToDo> listToDosByStatus(int status);
}
