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
public class Tarea {
    private int Id;
    private String Nombre;
    private String Descripcion;
    private int Tiempo_Estimado;
    private int Tiempo_Real;

    public Tarea() {
    }

    public Tarea(int Id, String Nombre, String Descripcion, int Tiempo_Estimado, int Tiempo_Real) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Tiempo_Estimado = Tiempo_Estimado;
        this.Tiempo_Real = Tiempo_Real;
   }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getTiempo_Estimado() {
        return Tiempo_Estimado;
    }

    public int getTiempo_Real() {
        return Tiempo_Real;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setTiempo_Estimado(int Tiempo_Estimado) {
        this.Tiempo_Estimado = Tiempo_Estimado;
    }

    public void setTiempo_Real(int Tiempo_Real) {
        this.Tiempo_Real = Tiempo_Real;
    }
       
    
}
