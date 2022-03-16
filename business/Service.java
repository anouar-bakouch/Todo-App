package com.gl.todo_.business;

import com.gl.todo_.beans.Todo;

import java.sql.SQLException;
import java.util.List;

public interface Service {

    //Todo

    Todo add (Todo todo) throws SQLException;

    Todo delete (Todo todo) throws SQLException;

    Todo update (Todo todo) throws SQLException;

    Todo getTodo(String name) throws SQLException;

    List<Todo> getTodos() throws SQLException;

}
