package com.gl.todo_.beans;

public class Todo {

    private String nom;
    private String description;

    public Todo(String nom,String description){

        this.nom = nom;
        this.description = description;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        Todo todo = (Todo) obj;
        return this.nom.equals(todo.nom);
    }
}
