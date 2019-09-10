/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import com.taskmanager.view.ViewTaskDev;
import com.taskmanager.view.ViewTaskLider;
import java.awt.event.ActionListener;
import com.taskmanager.view.Login;
import com.taskmanager.model.Usuario;
import java.awt.event.ActionEvent;
import com.taskmanager.controller.UserController;
import com.taskmanager.dao.UsuarioDAO;
import com.taskmanager.view.CreateUser;
import javax.swing.JOptionPane;


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
       // this.loginView.btnCreateUsuario.addActionListener(this);    
    }
    
    public void iniciar(){
    
        loginView.setTitle("LOGIN");
        loginView.setLocationRelativeTo(null);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {    
      if (e.getSource() == this.loginView.btnLogin) {
          
          userModel.setUsuario(loginView.user.getText());
          userModel.setContrasenia(loginView.password.getText());
          
          UsuarioDAO usuarioDAO =new UsuarioDAO();
          userModel = usuarioDAO.login(userModel);

          if (userModel.getIdRol() == 1) {
             ViewTaskDev frmDev = new ViewTaskDev();
              devController ac = new devController(frmDev,userModel);
              ac.iniciar();
              frmDev.setVisible(true);
          } else if (userModel.getIdRol() == 2 || userModel.getIdRol() == 3) {
              ViewTaskLider frmLider = new ViewTaskLider();
              AdminController ac = new AdminController(frmLider,userModel);
              ac.iniciar();
              frmLider.setVisible(true);   
          }
          else {
              JOptionPane.showMessageDialog(loginView, "Usuario o clave inválido", "Error",JOptionPane.ERROR_MESSAGE);
          }
          loginView.dispose();

          
      }/*else if(e.getSource() == this.loginView.btnCreateUsuario){
      
          UserController uc = new UserController(createUserView, userModel);
          uc.iniciar();
          loginView.setVisible(false);
          createUserView.setVisible(true);
      
      }*/
      
    }
    
   
}
