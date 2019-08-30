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
public class Usuario {
    private int Id;
    private String Nombre;
    private String Usuario;
    private String Contrasenia;
    private int activo;

    public Usuario() {
    }
    
    public Usuario(int Id, String Nombre, String Usuario, String Contrasenia, int activo) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Usuario = Usuario;
        this.Contrasenia = Contrasenia;
        this.activo = activo;
    }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public int getActivo() {
        return activo;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    
}
