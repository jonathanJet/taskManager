/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.dao;

import com.taskmanager.model.Rol;
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
public class RolDAO {
    
    private Connection connection;

    public RolDAO() {
        connection = ConexionDB.conectar();
    }

    public void crearRol(Rol rol) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("insert into tbl_roles(nombre_rol, activo) values (?,?)");

            // Parameters start with 1
            preparedStatement.setString(1, rol.getNombre());
            preparedStatement.setInt(2, rol.getActivo());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarRol(int rolId) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("delete from tbl_roles where id_rol=?");

            // Parameters start with 1
            preparedStatement.setInt(1, rolId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarRol(Rol rol) {
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("update tbl_roles set nombre_rol=?, activo=?" +
                    "where id_rol=?");

            // Parameters start with 1
            preparedStatement.setString(1, rol.getNombre());
            preparedStatement.setInt(2, rol.getActivo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rol> traerRoles() {
        List<Rol> roles = new ArrayList<Rol>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_roles");
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setID(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre_rol"));
               
                roles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Rol rolXId(int rolId) {
        Rol rol = new Rol();
        try {

            PreparedStatement preparedStatement = 
                    connection.prepareStatement("select * from tbl_roles where id_rol=?");

            preparedStatement.setInt(1, rolId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rol.setID(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre_rol"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }   

}
