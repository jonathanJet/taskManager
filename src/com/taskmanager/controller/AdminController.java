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
import com.taskmanager.view.CreateUser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
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
        
        this.AdminView.btnCrearTarea.addActionListener(this);
        this.AdminView.btnCrearUsuario.addActionListener(this);    
    
    }
    
    public void iniciar(){
    
        AdminView.setTitle("Manejador de Tareas");
        AdminView.setLocationRelativeTo(null);
        
        AdminView.lblUsuario.setText("Usuario: " + userModel.getNombre());
        AdminView.lblRol.setText(usuarioDAO.getUserRol(userModel.getId()));
        
        if(AdminView.lblRol.getText().equals("Administrador")){
            AdminView.btnCrearUsuario.setVisible(true);
        }
        else{
            AdminView.btnCrearUsuario.setVisible(false);
        }
        
    
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
        
        final DefaultTableModel model = new DefaultTableModel();
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
        AdminView.listTask.getColumnModel().getColumn(6).setWidth(0);
        AdminView.listTask.getColumnModel().getColumn(6).setMinWidth(0);
        AdminView.listTask.getColumnModel().getColumn(6).setMaxWidth(0);
        AdminView.listTask.getColumnModel().getColumn(7).setWidth(0);
        AdminView.listTask.getColumnModel().getColumn(7).setMinWidth(0);
        AdminView.listTask.getColumnModel().getColumn(7).setMaxWidth(0);
        AdminView.listTask.setBackground(Color.GRAY);
        AdminView.listTask.setForeground(Color.WHITE);
        AdminView.listTask.setRowHeight(25);

        AdminView.listTask.setDefaultEditor(Object.class, null);

        AdminView.listTask.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e){
                String estadosel = "" , responsablesel="" ;
                int i = AdminView.listTask.getSelectedRow();
                AdminView.txtnombre.setText(model.getValueAt(i, 1).toString());
                AdminView.txtDescripcion.setText(model.getValueAt(i, 2).toString());
                AdminView.txtTiempoEstimado.setText(model.getValueAt(i, 3).toString());
                
                for (int j = 0; j < AdminView.jEstados.getItemCount(); j++) {
                    estadosel = AdminView.jEstados.getItemAt(j).toString();
                    if (estadosel.contains(model.getValueAt(i, 8).toString())){
                        AdminView.jEstados.setSelectedItem(estadosel);
                        break;
                    }
                }
                
                for (int j = 0; j < AdminView.jResponsables.getItemCount(); j++) {
                    responsablesel = AdminView.jResponsables.getItemAt(j).toString();
                    if (responsablesel.contains(model.getValueAt(i, 5).toString())){
                        AdminView.jResponsables.setSelectedItem(responsablesel);
                        break;
                    }
                }
            }
        });
        
        this.AdminView.btnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AdminView.txtnombre.setText("");
                AdminView.txtDescripcion.setText("");
                AdminView.txtTiempoEstimado.setText("");
                AdminView.jEstados.setSelectedIndex(-1); 
                AdminView.jResponsables.setSelectedIndex(-1);
                AdminView.listTask.getSelectionModel().clearSelection();
                model.setRowCount(0);

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
            }
        });
        
        this.AdminView.btnModificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (AdminView.listTask.getSelectedRow() != -1){
                    if (!AdminView.txtTiempoEstimado.getText().equals("")){
                        if(AdminView.jEstados.getSelectedIndex() != -1) {
                            int i = AdminView.listTask.getSelectedRow();
                            if(Integer.parseInt(model.getValueAt(i, 4).toString()) == 0){
                                  model.setValueAt(AdminView.txtnombre.getText(), i, 1);
                                  model.setValueAt(AdminView.txtDescripcion.getText(), i, 2);
                                  model.setValueAt(AdminView.txtTiempoEstimado.getText(), i, 3);

                                  String[] parts1 = AdminView.jResponsables.getSelectedItem().toString().split("-");
                                  model.setValueAt((parts1[1].trim().toString()), i, 5);

                                  String[] parts = AdminView.jEstados.getSelectedItem().toString().split("-");
                                  model.setValueAt((parts[1].trim().toString()), i, 8);

                                  tarea.setNombre(AdminView.txtnombre.getText());
                                  tarea.setDescripcion(AdminView.txtDescripcion.getText());
                                  tarea.setTiempo_Estimado(Integer.parseInt(AdminView.txtTiempoEstimado.getText()));
                                  tarea.setIdEstado(Integer.parseInt(parts[0].trim()));
                                  tarea.setIdUsuario(Integer.parseInt(parts1[0].trim()));
                                  tarea.setId(Integer.parseInt(model.getValueAt(i, 0).toString()));

                                  tareaDao.actualizarTarea(tarea);

                                  AdminView.txtnombre.setText("");
                                  AdminView.txtDescripcion.setText("");
                                  AdminView.txtTiempoEstimado.setText("");
                                  AdminView.jEstados.setSelectedIndex(-1); 
                                  AdminView.jResponsables.setSelectedIndex(-1);
                                  AdminView.listTask.getSelectionModel().clearSelection();
                            }
                            else{
                                JOptionPane.showMessageDialog(AdminView, "La tarea ya está siendo realizada por " + model.getValueAt(i, 5).toString() , "Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(AdminView, "Debe seleccionar un estado de la tarea", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(AdminView, "Debe ingresar el tiempo real de la tarea", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(AdminView, "Debe seleccionar una tarea de la lista", "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
       if (e.getSource() == this.AdminView.btnCrearTarea) {
           CreateTask frmTask = new CreateTask();
           TaskController ac = new TaskController(frmTask,userModel);
           ac.iniciar();
           frmTask.setVisible(true);
           this.AdminView.setVisible(false);
          
      }
      if (e.getSource() == this.AdminView.btnCrearUsuario) {
            CreateUser frmUser = new CreateUser();
            UserController ac = new UserController(frmUser,userModel);
            ac.iniciar();
            frmUser.setVisible(true);
            this.AdminView.setVisible(false);
      }
    }  
    
}
