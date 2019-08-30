/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import com.taskmanager.dao.EstadoDAO;
import com.taskmanager.dao.UsuarioDAO;
import com.taskmanager.model.Estado;
import com.taskmanager.view.ViewTaskLider;
import com.taskmanager.model.Usuario;
import com.taskmanager.view.CreateTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Keylis
 */
public class AdminController implements ActionListener {
    
    private ViewTaskLider AdminView;
    private Usuario userModel;
    
    private EstadoDAO estadoDAO = new EstadoDAO();
    private Estado estado;
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario;
    
    public AdminController(ViewTaskLider viewTaskLider,Usuario user){
    
        this.AdminView = viewTaskLider;
        this.userModel = user;
        
        /*this.loginView.btnLogin.addActionListener(this);
        this.loginView.btnCreateUsuario.addActionListener(this);    
    */
    }
    
    public void iniciar(){
    
        AdminView.setTitle("ADMINISTRADOR");
        AdminView.setLocationRelativeTo(null);
    
        AdminView.jEstados.removeAllItems();
        for (int i = 0; i < estadoDAO.traerEstados().size() ; i++) {
            estado =new Estado();
            estado = estadoDAO.traerEstados().get(i);
            AdminView.jEstados.addItem(estado.getId()+"-"+estado.getEstado());
        }   
        
        AdminView.jResponsables.removeAllItems();
        for (int i = 0; i < usuarioDAO.traerResponsables().size() ; i++) {
            usuario =new Usuario();
            usuario = usuarioDAO.traerResponsables().get(i);
            AdminView.jResponsables.addItem(usuario.getId()+"-"+usuario.getNombre());
        }   
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
       if (e.getSource() == this.AdminView.btnCrear) {
         
           CreateTask frmTask = new CreateTask();
           TaskController ac = new TaskController(frmTask,userModel);
            ac.iniciar();
            frmTask.setVisible(true);
          
      }
    }  
    
}
