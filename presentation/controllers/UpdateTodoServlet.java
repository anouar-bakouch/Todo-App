package com.gl.todo_.presentation.controllers;

import com.gl.todo_.beans.Todo;
import com.gl.todo_.business.DefaultServices;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateTodo")
@MultipartConfig
public class UpdateTodoServlet extends HttpServlet {

    private DefaultServices defaultServices;

    @Override
    public void init() throws ServletException {
        try {
            this.defaultServices = DefaultServices.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Todo todo = new Todo(nom, description);

        if (defaultServices.update(todo) == null)
            resp.getWriter().append("0");
        else
            resp.getWriter().append("1");

    } catch(Exception e){
        e.printStackTrace();
    }

    }


}











