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
            connection.prepareStatement("insert into tbl_tarea(nombre,descripcion,tiempo_estimado,tiempo_real,id_estado) values (?, ?, ?, ?, ? )");

            // Parameters start with 1
            preparedStatement.setString(1, tarea.getNombre());
            preparedStatement.setString(2, tarea.getDescripcion());
            preparedStatement.setInt(3, tarea.getTiempo_Estimado());
            //preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setInt(4, tarea.getTiempo_Real());
            preparedStatement.setInt(5, tarea.getIdEstado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }/*

     public void addUserRol(Usuario user) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("insert into tbl_usuario_rol(id_usuario,id_rol) values (?, ?)");

            // Parameters start with 1
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getIdRol());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void updateUser(Usuario user) {
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("update tbl_usuario set nombre=?, usuario=?, contrasenia=?, activo=?" +
                    "where id_usuario=?");

            // Parameters start with 1
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.setString(2, user.getUsuario());
            preparedStatement.setString(3, user.getContrasenia());
            //preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setInt(4, user.getActivo());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> getAllUsers() {
        List<Usuario> users = new ArrayList<Usuario>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select a.id_rol,b.* from tbl_usuario_rol a, tbl_usuario b where a.id_usuario =b.id_usuario");
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setUsuario(rs.getString("usuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setActivo(rs.getInt("activo"));
                user.setIdRol(rs.getInt("id_rol"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

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
