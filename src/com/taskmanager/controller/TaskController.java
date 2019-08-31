/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskmanager.controller;

import com.taskmanager.dao.EstadoDAO;
import com.taskmanager.dao.TareaDAO;
import com.taskmanager.dao.UsuarioDAO;
import com.taskmanager.model.Estado;
import com.taskmanager.model.Tarea;
import com.taskmanager.model.Usuario;
import com.taskmanager.view.CreateTask;
import com.taskmanager.view.ViewTaskDev;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Keylis
 */
public class TaskController implements ActionListener{
    
    private CreateTask taskView;
    private Usuario userModel;
    private Tarea tareaModel = new Tarea();
    
    private EstadoDAO estadoDAO = new EstadoDAO();
    private Estado estado;
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario;

    public TaskController(CreateTask taskView,Usuario user){
    
        this.taskView = taskView;
        this.userModel = user;
        
        this.taskView.btnCrearTarea.addActionListener(this);
       /* this.loginView.btnCreateUsuario.addActionListener(this);    
    */
    }
    
    public void iniciar(){
    
        taskView.setTitle("DESARROLLADOR");
        taskView.setLocationRelativeTo(null);
    
     
        taskView.jEstados.removeAllItems();
        for (int i = 0; i < estadoDAO.traerEstados().size() ; i++) {
            estado =new Estado();
            estado = estadoDAO.traerEstados().get(i);
            taskView.jEstados.addItem(estado.getId()+"-"+estado.getEstado());
        }   
        
        taskView.jResponsables.removeAllItems();
        for (int i = 0; i < usuarioDAO.traerResponsables().size() ; i++) {
            usuario =new Usuario();
            usuario = usuarioDAO.traerResponsables().get(i);
            taskView.jResponsables.addItem(usuario.getId()+"-"+usuario.getNombre());
        }   
        
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
       
      if (e.getSource() == this.taskView.btnCrearTarea) {
          
          tareaModel.setNombre(taskView.txtnombre.getText());
          tareaModel.setDescripcion(taskView.txtDescripcion.getText());
          tareaModel.setTiempo_Estimado(Integer.parseInt(taskView.txtTiempoEstimado.getText()));
          
          
          String[] parts = taskView.jEstados.getSelectedItem().toString().split("-");
          tareaModel.setIdEstado(Integer.parseInt(parts[0].trim())); 
          
          String[] parts1 = taskView.jResponsables.getSelectedItem().toString().split("-");
          tareaModel.setIdUsuario(Integer.parseInt(parts1[0].trim())); 
          
          TareaDAO tareaDAO =new TareaDAO();
          tareaDAO.crearTarea(tareaModel);
          
          JOptionPane.showMessageDialog(this.taskView, "Registro creado con éxito", "Error",JOptionPane.ERROR_MESSAGE);
          this.taskView.dispose();
      }
    }  
    
}
