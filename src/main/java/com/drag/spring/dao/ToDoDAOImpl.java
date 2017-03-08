package com.drag.spring.dao;

import com.drag.spring.model.ToDo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ToDoDAOImpl implements ToDoDAO {

    private static final Logger logger = LoggerFactory.getLogger(ToDoDAOImpl.class);

    private SessionFactory sessionFactory;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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
/*        Session session = this.sessionFactory.getCurrentSession();
        String filter = "from ToDo";
        if (status == 0 || status == 1)
            filter += " WHERE isIsdone = " + status;*/
        String sql = "select id, title, description, done from todolist";
        if (status == 0 || status == 1)
            sql += " WHERE done = " + status;

        List<ToDo> list = namedParameterJdbcTemplate.query(sql, new ToDoMapper());
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

    private static final class ToDoMapper implements RowMapper<ToDo> {

        @Override
        public ToDo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            ToDo toDo = new ToDo();
            toDo.setId(resultSet.getInt("id"));
            toDo.setTitle(resultSet.getString("title"));
            toDo.setDescription(resultSet.getString("description"));
            toDo.setDone(resultSet.getBoolean("done"));

            return toDo;
        }
    }
}
