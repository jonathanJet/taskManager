/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.dao;

import com.taskmanager.model.Tarea;
import com.taskmanager.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author administrador
 */
public class TareaDAO {
    
    private Connection connection;

    public TareaDAO() {
        connection = ConexionDB.conectar();
    }

    public void crearTarea(Tarea tarea) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("insert into tbl_tarea(id_tarea,nombre,descripcion,tiempo_estimado,tiempo_real,id_usuario,id_estado) values ((select coalesce(max(id_tarea),0) +1 from tbl_tarea), ?, ?, ?, 0, ?, ? )");

            // Parameters start with 1
            preparedStatement.setString(1, tarea.getNombre());
            preparedStatement.setString(2, tarea.getDescripcion());
            preparedStatement.setInt(3, tarea.getTiempo_Estimado());
            //preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
             preparedStatement.setInt(4, tarea.getIdUsuario());
            preparedStatement.setInt(5, tarea.getIdEstado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 /*
    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("delete from tbl_usuario where id_usuario=?");

            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    
    public void actualizarTarea(Tarea tarea) {
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("update tbl_tarea set nombre=?, descripcion=?, tiempo_estimado=?, id_usuario=?, id_estado=?" +
                    " where id_tarea=?");

            // Parameters start with 1
            preparedStatement.setString(1, tarea.getNombre());
            preparedStatement.setString(2, tarea.getDescripcion());
            preparedStatement.setInt(3, tarea.getTiempo_Estimado());
            //preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setInt(4, tarea.getIdUsuario());
            preparedStatement.setInt(5, tarea.getIdEstado());
            preparedStatement.setInt(6, tarea.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarTareas(javax.swing.JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla = new javax.swing.JTable(modelo);
   
        modelo.addColumn("Código");
        modelo.addColumn("Tarea");
        modelo.addColumn("Descripción");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Tiempo Real");
        modelo.addColumn("Responsable");
        modelo.addColumn("id_usuario");
        modelo.addColumn("id_estado");
        modelo.addColumn("Estado");

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_tarea, a.nombre Tarea, a.descripcion, a.tiempo_estimado, a.tiempo_real, b.nombre, b.id_usuario,c.id_estado,c.nombre_estado from tbl_tarea a, tbl_usuario b, tbl_estado c where a.id_usuario = b.id_usuario and a.id_estado = c.id_estado");
            
            while (rs.next()) {
            // Se crea un array que será una de las filas de la tabla. 
                Object [] fila = new Object[9]; // Hay seis columnas en la tabla
                // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
                for (int i=0;i<9;i++)
                     fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
                // Se añade al modelo la fila completa.
                    modelo.addRow(fila); 
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Tarea> cargarTareas() {
        List<Tarea> tareas = new ArrayList<Tarea>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_tarea, a.nombre Tarea, a.descripcion, a.tiempo_estimado, a.tiempo_real, b.nombre, b.id_usuario,c.id_estado,c.nombre_estado from tbl_tarea a, tbl_usuario b, tbl_estado c where a.id_usuario = b.id_usuario and a.id_estado = c.id_estado");
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId(rs.getInt("id_tarea"));
                tarea.setNombre(rs.getString("Tarea"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setTiempo_Estimado(rs.getInt("tiempo_estimado"));
                tarea.setTiempo_Real(rs.getInt("tiempo_real"));
                tarea.setIdUsuario(rs.getInt("id_usuario"));
                tarea.setIdEstado(rs.getInt("id_estado"));               
                tarea.setUsuario(rs.getString("nombre") );
                tarea.setEstado(rs.getString("nombre_estado"));
                
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }
    
     public List<Tarea> cargarTareasXUsuario(Integer userID) {
        List<Tarea> tareas = new ArrayList<Tarea>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_tarea, a.nombre Tarea, a.descripcion, a.tiempo_estimado, a.tiempo_real, b.nombre, b.id_usuario,c.id_estado,c.nombre_estado from tbl_tarea a, tbl_usuario b, tbl_estado c where a.id_usuario = b.id_usuario and a.id_estado = c.id_estado and a.id_usuario="+ userID);
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId(rs.getInt("id_tarea"));
                tarea.setNombre(rs.getString("Tarea"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setTiempo_Estimado(rs.getInt("tiempo_estimado"));
                tarea.setTiempo_Real(rs.getInt("tiempo_real"));
                tarea.setIdUsuario(rs.getInt("id_usuario"));
                tarea.setIdEstado(rs.getInt("id_estado"));               
                tarea.setUsuario(rs.getString("nombre") );
                tarea.setEstado(rs.getString("nombre_estado"));
                
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }
     
    public void actualizarTareaResponsable(Tarea tarea) {
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("update tbl_tarea set tiempo_real=? , id_estado= ? where id_tarea=?" );

            // Parameters start with 1
            preparedStatement.setInt(1, tarea.getTiempo_Real());
            preparedStatement.setInt(2, tarea.getIdEstado());
            preparedStatement.setInt(3, tarea.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  
/*
    public Usuario getUserById(int userId) {
        Usuario user = new Usuario();
        try {

            PreparedStatement preparedStatement = 
                    connection.prepareStatement("select a.id_rol,b.* from tbl_usuario_rol a, tbl_usuario b where a.id_usuario =b.id_usuario and id_usuario=?");

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setUsuario(rs.getString("usuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setActivo(rs.getInt("activo"));
                user.setIdRol(rs.getInt("id_rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }*/
}
