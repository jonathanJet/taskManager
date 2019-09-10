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
import com.taskmanager.view.ViewTaskDev;
import com.taskmanager.model.Usuario;
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
public class devController implements ActionListener    {
    
    private ViewTaskDev devView;
    private Usuario userModel;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    private EstadoDAO estadoDAO = new EstadoDAO();
    private Estado estado;
     
    private TareaDAO tareaDao;
    private Tarea tarea = new Tarea();
    
     public devController(ViewTaskDev viewTaskLider,Usuario user){
    
        this.devView = viewTaskLider;
        this.userModel = user;
       
    }
    
    public void iniciar(){
        tareaDao = new TareaDAO();
        devView.setTitle("Manejador de Tareas");
        devView.setLocationRelativeTo(null);
        
        devView.lblUsuario.setText("Usuario: " + userModel.getNombre());
        devView.lblRol.setText(usuarioDAO.getUserRol(userModel.getId()));
        
    
        devView.jEstados.removeAllItems();
        for (int i = 0; i < estadoDAO.traerEstados().size() ; i++) {
            estado =new Estado();
            estado = estadoDAO.traerEstados().get(i);
            devView.jEstados.addItem(estado.getId()+" - "+estado.getEstado());
        }      
        devView.jEstados.setSelectedIndex(-1);
         
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
        for(int i = 0; i < tareaDao.cargarTareasXUsuario(this.userModel.getId()).size(); i++){
           tarea = tareaDao.cargarTareasXUsuario(this.userModel.getId()).get(i);
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
        
        devView.listTask.setModel(model);
        devView.listTask.getColumnModel().getColumn(6).setWidth(0);
        devView.listTask.getColumnModel().getColumn(6).setMinWidth(0);
        devView.listTask.getColumnModel().getColumn(6).setMaxWidth(0);
        devView.listTask.getColumnModel().getColumn(7).setWidth(0);
        devView.listTask.getColumnModel().getColumn(7).setMinWidth(0);
        devView.listTask.getColumnModel().getColumn(7).setMaxWidth(0);
        devView.listTask.setBackground(Color.LIGHT_GRAY);
        devView.listTask.setForeground(Color.BLACK);
        devView.listTask.setRowHeight(25);
        
        devView.listTask.setDefaultEditor(Object.class, null);

        devView.listTask.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e){
                String estadosel = "";
                int i = devView.listTask.getSelectedRow();
                devView.txtTiempoReal.setText(model.getValueAt(i, 4).toString());
                
                for (int j = 0; j < devView.jEstados.getItemCount(); j++) {
                    estadosel = devView.jEstados.getItemAt(j).toString();
                    if (estadosel.contains(model.getValueAt(i, 8).toString())){
                        devView.jEstados.setSelectedItem(estadosel);
                        break;
                    }
                }    
            }
        });
        this.devView.btnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                devView.txtTiempoReal.setText("");
                devView.jEstados.setSelectedIndex(-1); 
                devView.listTask.getSelectionModel().clearSelection();
                model.setRowCount(0);

                Object[] rowData = new Object[9];
                for(int i = 0; i < tareaDao.cargarTareasXUsuario(userModel.getId()).size(); i++){
                   tarea = tareaDao.cargarTareasXUsuario(userModel.getId()).get(i);
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
       
        
       this.devView.btnModificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (devView.listTask.getSelectedRow() != -1){
                    if (!devView.txtTiempoReal.getText().equals("")){
                        if(devView.jEstados.getSelectedIndex() != -1) {
                            int i = devView.listTask.getSelectedRow();
                            String[] parts = devView.jEstados.getSelectedItem().toString().split("-");
                            model.setValueAt((parts[1].trim().toString()), i, 8);
                            model.setValueAt(devView.txtTiempoReal.getText(), i, 4);
                            
                            tarea.setTiempo_Real(Integer.parseInt(devView.txtTiempoReal.getText()));
                            tarea.setIdEstado(Integer.parseInt(parts[0].trim()));
                            tarea.setId(Integer.parseInt(model.getValueAt(i, 0).toString()));

                            tareaDao.actualizarTareaResponsable(tarea);
                            
                            devView.txtTiempoReal.setText("");
                            devView.jEstados.setSelectedIndex(-1); 
                            devView.listTask.getSelectionModel().clearSelection();
                        }
                        else{
                            JOptionPane.showMessageDialog(devView, "Debe seleccionar un estado", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(devView, "Debe ingresar el tiempo real", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(devView, "Debe seleccionar una fila de la tabla", "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

               
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
 
    }  
    
}
