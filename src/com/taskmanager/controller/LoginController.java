/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import java.awt.event.ActionListener;
import com.taskmanager.view.Login;
import com.taskmanager.model.Usuario;
import java.awt.event.ActionEvent;
import com.taskmanager.controller.UserController;
import com.taskmanager.view.CreateUser;


/**
 *
 * @author Keylis
 */
public class LoginController implements ActionListener{
    
    private Login loginView;
    private Usuario userModel;
        private CreateUser createUserView = new CreateUser();

    
    public LoginController(Login login,Usuario user){
    
        this.loginView = login;
        this.userModel = user;
        
        this.loginView.btnLogin.addActionListener(this);
        this.loginView.btnCreateUsuario.addActionListener(this);

        
    }
    
    public void iniciar(){
    
        loginView.setTitle("LOGIN");
        loginView.setLocationRelativeTo(null);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {    
           
      if (e.getSource() == this.loginView.btnLogin) {
      
          System.out.println("Ventana restaurada");
          
      }else if(e.getSource() == this.loginView.btnCreateUsuario){
      
          UserController uc = new UserController(createUserView, userModel);
          uc.iniciar();
          loginView.setVisible(false);
          createUserView.setVisible(true);
      
      }
      
    }
    
   
}
