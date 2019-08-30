/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.model;

/**
 *
 * @author administrador
 */
public class Rol {
    private int ID;
    private String Nombre;
    private int Activo;

    public Rol() {
    }

    public Rol(int ID, String Nombre, int Activo) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Activo = Activo;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getActivo() {
        return Activo;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }
    
    
}
