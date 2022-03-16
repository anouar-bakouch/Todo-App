package com.gl.todo_.business;

import com.gl.todo_.beans.Todo;
import com.gl.todo_.dao.TodoDao;
import com.gl.todo_.dao.TodoDaoJdbc;
import java.sql.SQLException;
import java.util.List;

public class DefaultServices implements Service{

    private TodoDao todoDao;
    private static DefaultServices instance;

    public static DefaultServices getInstance() throws SQLException {

        if(instance == null) instance = new DefaultServices();

        return instance;
    }

    private DefaultServices() throws SQLException {
        this.todoDao = new TodoDaoJdbc();
    }

    @Override
    public Todo add(Todo todo) throws SQLException {
        return todoDao.add(todo);
    }

    @Override
    public Todo delete(Todo todo) throws SQLException {
        return todoDao.delete(todo);
    }

    @Override
    public Todo update(Todo todo) throws SQLException {
        return todoDao.update(todo);
    }

    @Override
    public Todo getTodo(String name) throws SQLException {
        return todoDao.getTodo(name);
    }

    @Override
    public List<Todo> getTodos() throws SQLException {
        return todoDao.getTodos();
    }
}
