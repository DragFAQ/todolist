package com.drag.spring.service;

import com.drag.spring.dao.ToDoDAO;
import com.drag.spring.model.ToDo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    private ToDoDAO toDoDAO;

    public void setToDoDAO(ToDoDAO toDoDAO) {
        this.toDoDAO = toDoDAO;
    }

    @Transactional
    public void addToDo(ToDo toDo) {
        this.toDoDAO.addToDo(toDo);
    }

    @Transactional
    public void updateToDo(ToDo toDo) {
        this.toDoDAO.updateToDo(toDo);
    }

    @Transactional
    public void removeToDo(int id) {
        this.toDoDAO.removeToDo(id);
    }

    @Transactional
    public ToDo getToDoById(int id) {
        return this.toDoDAO.getToDoById(id);
    }

    @Transactional
    public List<ToDo> listToDosByStatus(int status) {
        return this.toDoDAO.listToDosByStatus(status);
    }
}
