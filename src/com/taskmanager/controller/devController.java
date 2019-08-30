/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import com.taskmanager.dao.EstadoDAO;
import com.taskmanager.model.Estado;
import com.taskmanager.view.ViewTaskDev;
import com.taskmanager.model.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Keylis
 */
public class devController implements ActionListener    {
    
     private ViewTaskDev devView;
     private Usuario userModel;
    
     private EstadoDAO estadoDAO = new EstadoDAO();
     private Estado estado;
    
     public devController(ViewTaskDev viewTaskLider,Usuario user){
    
        this.devView = viewTaskLider;
        this.userModel = user;
        
        /*this.loginView.btnLogin.addActionListener(this);
        this.loginView.btnCreateUsuario.addActionListener(this);    
    */
    }
    
    public void iniciar(){
    
        devView.setTitle("DESARROLLADOR");
        devView.setLocationRelativeTo(null);
    
        devView.jEstados.removeAllItems();
        for (int i = 0; i < estadoDAO.traerEstados().size() ; i++) {
            estado =new Estado();
            estado = estadoDAO.traerEstados().get(i);
            devView.jEstados.addItem(estado.getId()+"-"+estado.getEstado());
        }       
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
        
    }  
    
}
