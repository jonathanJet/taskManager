/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import com.taskmanager.model.Usuario;
import com.taskmanager.view.CreateUser;
import com.taskmanager.view.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Keylis
 */
public class UserController implements ActionListener{

    private CreateUser createUserView;
    private Usuario userModel;
        private Login loginView;

    
    public UserController(CreateUser view,Usuario model){
    
        this.createUserView = view;
        this.userModel = model;
        
        this.createUserView.btnCrearUsuario.addActionListener(this);
        //this.loginView.btnCreateUsuario.addActionListener(this);
        
    }
    
    public void iniciar(){
        
        System.out.println("Ventana restaurada crear");
        createUserView.setTitle("Crear Usuario");
        createUserView.setLocationRelativeTo(null); 
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {    
           
      if (e.getSource() == this.createUserView.btnCrearUsuario) {
      
          System.out.println(createUserView.txtnombre.getText());
          //SAVE HEREs
          
          loginView = new Login();
          
          LoginController lc = new LoginController(loginView, new Usuario());
          lc.iniciar();
          createUserView.setVisible(false);
          loginView.setVisible(true);

          
      }
      
    }
    
}
