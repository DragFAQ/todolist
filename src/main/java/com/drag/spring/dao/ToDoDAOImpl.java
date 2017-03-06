package com.drag.spring.dao;

import com.drag.spring.model.ToDo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Override
    public List<ToDo> listToDosByStatus(Integer offset, Integer maxResults) {
        List<ToDo> list = sessionFactory.openSession()
                .createCriteria(ToDo.class)
                .setFirstResult(offset != null ? offset : 0)
                .setMaxResults(maxResults != null ? maxResults : 10)
                .list();
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

    @Override
    public Long count() {
        return (Long) sessionFactory.openSession()
                .createCriteria(ToDo.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
