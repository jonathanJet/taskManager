/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import com.taskmanager.model.Usuario;
import com.taskmanager.view.CreateTask;
import com.taskmanager.view.ViewTaskDev;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Keylis
 */
public class TaskController implements ActionListener{
    
    private CreateTask taskView;
     private Usuario userModel;
    
    public TaskController(CreateTask taskView,Usuario user){
    
        this.taskView = taskView;
        this.userModel = user;
        
        /*this.loginView.btnLogin.addActionListener(this);
        this.loginView.btnCreateUsuario.addActionListener(this);    
    */
    }
    
    public void iniciar(){
    
        taskView.setTitle("DESARROLLADOR");
        taskView.setLocationRelativeTo(null);
    
        /*devView.jEstados.removeAllItems();
        for (int i = 0; i < estadoDAO.traerEstados().size() ; i++) {
            estado =new Estado();
            estado = estadoDAO.traerEstados().get(i);
            devView.jEstados.addItem(estado.getId()+"-"+estado.getEstado());
        }*/       
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
      
    }  
    
}
