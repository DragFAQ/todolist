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

    @Override
    @Transactional
    public void addToDo(ToDo toDo) {
        this.toDoDAO.addToDo(toDo);
    }

    @Override
    @Transactional
    public void updateToDo(ToDo toDo) {
        this.toDoDAO.updateToDo(toDo);
    }

    @Override
    @Transactional
    public void removeToDo(int id) {
        this.toDoDAO.removeToDo(id);
    }

    @Override
    @Transactional
    public ToDo getToDoById(int id) {
        return this.toDoDAO.getToDoById(id);
    }

    @Override
    @Transactional
    public List<ToDo> listToDosByStatus(Integer offset, Integer maxResults) {
        return this.toDoDAO.listToDosByStatus(offset, maxResults);
    }

    @Override
    @Transactional
    public void setDone(int id) {
        this.toDoDAO.setDone(id);
    }

    @Override
    public Long count() {
        return this.toDoDAO.count();
    }
}
