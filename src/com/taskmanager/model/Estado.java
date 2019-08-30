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
public class Estado {
    private int Id;
    private String estado;

    public Estado() {
    }

    public Estado(int Id, String estado) {
        this.Id = Id;
        this.estado = estado;
    }

    public int getId() {
        return Id;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
