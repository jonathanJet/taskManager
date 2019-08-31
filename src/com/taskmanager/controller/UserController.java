/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import com.taskmanager.dao.RolDAO;
import com.taskmanager.model.*;
import com.taskmanager.dao.UsuarioDAO;
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
    private RolDAO rolDAO = new RolDAO();
    private Rol rol;
    
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
       
        createUserView.roles.removeAllItems();
        for (int i = 0; i < rolDAO.traerRoles().size() ; i++) {
            rol =new Rol();
            rol = rolDAO.traerRoles().get(i);
            createUserView.roles.addItem(rol.getID()+"-"+rol.getNombre());
        }       
    }

    @Override
    public void actionPerformed(ActionEvent e) {    
           
      if (e.getSource() == this.createUserView.btnCrearUsuario) {
          System.out.println(createUserView.txtnombre.getText());
          
          userModel.setNombre(createUserView.txtnombre.getText());
          userModel.setUsuario(createUserView.txtusuario.getText());
          userModel.setContrasenia(createUserView.txtcontrasenia.getText());
          userModel.setActivo(1);
          
          String[] parts = createUserView.roles.getSelectedItem().toString().split("-");
          userModel.setIdRol(Integer.parseInt(parts[0])); 
          UsuarioDAO usuarioDAO =new UsuarioDAO();
          usuarioDAO.crearUsuario(userModel);
          usuarioDAO.addUserRol(userModel);
          //SAVE HEREs
          
          loginView = new Login();
          
          LoginController lc = new LoginController(loginView, new Usuario());
          lc.iniciar();
          createUserView.setVisible(false);
          loginView.setVisible(true);

          
      }
      
    }
    
}
