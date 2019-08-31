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
import com.taskmanager.view.ViewTaskLider;
import com.taskmanager.model.Usuario;
import com.taskmanager.view.CreateTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

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
    
    private TareaDAO tareaDao = new TareaDAO();
    private Tarea tarea = new Tarea();
    
    public AdminController(ViewTaskLider viewTaskLider,Usuario user){
    
        this.AdminView = viewTaskLider;
        this.userModel = user;
        
        this.AdminView.btnCrear.addActionListener(this);
        /*this.loginView.btnCreateUsuario.addActionListener(this);    
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
        
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[9];
        
        columnsName[0] = "Código";
        columnsName[1] = "Tarea";
        columnsName[2] = "Descripción";
        columnsName[3] = "Tiempo Estimado";
        columnsName[4] = "Tiempo Real";
        columnsName[5] = "Responsable";
        columnsName[6] = "id_usuario";
        columnsName[7] = "id_estado";
        columnsName[8] = "Estado";
        
        model.setColumnIdentifiers(columnsName);
        
        Object[] rowData = new Object[9];
        for(int i = 0; i < tareaDao.cargarTareas().size(); i++){
           tarea = tareaDao.cargarTareas().get(i);
           rowData[0] = tarea.getId();
           rowData[1] = tarea.getNombre();
           rowData[2] = tarea.getDescripcion();
           rowData[3] = tarea.getTiempo_Estimado();
           rowData[4] = tarea.getTiempo_Real();
           rowData[5] = tarea.getUsuario();
           rowData[6] = tarea.getIdUsuario();
           rowData[7] = tarea.getIdEstado();
           rowData[8] = tarea.getEstado();

           model.addRow(rowData);
        }
        
        AdminView.listTask.setModel(model);
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
