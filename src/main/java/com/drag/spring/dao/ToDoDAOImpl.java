package com.drag.spring.dao;

import com.drag.spring.model.ToDo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToDoDAOImpl implements ToDoDAO {

    private static final Logger logger = LoggerFactory.getLogger(ToDoDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addToDo(ToDo toDo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(toDo);
        logger.info("TODO saved. [" + toDo + "]");
    }

    @Override
    public void updateToDo(ToDo toDo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(toDo);
        logger.info("TODO updated. [" + toDo + "]");
    }

    @Override
    public void removeToDo(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ToDo toDo = (ToDo) session.load(ToDo.class, new Integer(id));
        if (toDo != null)
            session.delete(toDo);
        logger.info("TODO deleted. [" + toDo + "]");
    }

    @Override
    public ToDo getToDoById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ToDo toDo = (ToDo) session.load(ToDo.class, new Integer(id));
        logger.info("TODO loaded. [" + toDo + "]");

        return toDo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ToDo> listToDosByStatus(int status) {
        Session session = this.sessionFactory.getCurrentSession();
        String filter = "from ToDo";
        if (status == 0 || status == 1)
            filter += " WHERE isDone = " + status;
        List<ToDo> list = session.createQuery(filter).list();
        for (ToDo t : list)
            logger.info("TODO List::" + t);

        return list;
    }

    @Override
    public void setDone(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ToDo toDo = (ToDo) session.load(ToDo.class, new Integer(id));
        if (toDo != null) {
            toDo.setDone(!toDo.isDone());
            session.update(toDo);
        }
    }
}
