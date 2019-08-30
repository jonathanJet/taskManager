/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskManager.mvc;

import com.taskmanager.controller.LoginController;
import com.taskmanager.view.Login;
import com.taskmanager.model.Usuario;

/**
 *
 * @author Keylis
 */
public class main {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Usuario user = new Usuario();
        Login login = new Login();
        
        LoginController controller = new LoginController( login, user);
        
        controller.iniciar();
        login.setVisible(true);
        
        
        
    }
    
}
