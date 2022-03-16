package com.gl.todo_.presentation.controllers;

import com.gl.todo_.beans.Todo;
import com.gl.todo_.business.DefaultServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listTodo")
@MultipartConfig
public class ListTodo extends HttpServlet {

    private DefaultServices defaultServices;
    private List<Todo> todos;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        try {
            defaultServices = DefaultServices.getInstance();
            this.gsonBuilder = new GsonBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    try {

        todos = defaultServices.getTodos();
        gson = gsonBuilder.create();
        String JSONtodos = gson.toJson(todos);

        resp.getWriter().append(JSONtodos);

    }
    catch(Exception e){
        e.printStackTrace();
    }

    }

}












