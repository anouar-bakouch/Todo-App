package com.gl.todo_.dao;
import com.gl.todo_.beans.Todo;
import com.gl.todo_.utils.MySql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoJdbc implements TodoDao{

    private MySql mySql;
    private Connection con;


    public TodoDaoJdbc() throws SQLException {
        this.mySql = MySql.getInstance();
        this.con = mySql.getConnection();
    }

    @Override
    public Todo add(Todo todo) throws SQLException {

        for(Todo todo_ : getTodos()){
            if(todo_.equals(todo)){
                System.out.println("yes yes yes");
                return null;
            }
        }

        String query = "insert into db_.todo values('"+todo.getNom()+"','"+todo.getDescription()+"')";
        Statement stmt = con.createStatement();

        stmt.executeUpdate(query);

        return todo;
    }

    @Override
    public Todo delete(Todo todo) throws SQLException {

        String query = "delete from db_.todo where nom = '"+todo.getNom()+"' ";
        Statement stmt = con.createStatement();

        for(Todo todo_ : getTodos()){
            if(todo_.equals(todo)){
                stmt.executeUpdate(query);
                return todo;
            }
        }

        return null;
    }

    @Override
    public Todo update(Todo todo) throws SQLException {
        String query = "update db_.todo set nom = '"+todo.getNom()+"' , description = '"+todo.getDescription()+"' where nom = '"+todo.getNom()+"' ";
        Statement stmt  =con.createStatement();
        for(Todo todo_ : getTodos()){
            if(todo_.equals(todo)){
              stmt.executeUpdate(query);
              return todo;
            }
        }
        return null;
    }

    @Override
    public Todo getTodo(String name) throws SQLException {

        String query = "select * from db_.todo where nom = '"+name+"'";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);

        if(!res.next()) return null;
        String nom = res.getString("nom");
        String description = res.getString("description");
        Todo todo = new Todo(nom,description);
        res.next();

        return todo;
    }

    @Override
    public List<Todo> getTodos() throws SQLException {

        List<Todo> todos = new ArrayList<>();
        String query = "select * from db_.todo";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while(res.next()){

            String nom = res.getString("nom");
            String description = res.getString("description");
            Todo todo = new Todo(nom,description);
            todos.add(todo);

        }

        return todos;
    }
}















