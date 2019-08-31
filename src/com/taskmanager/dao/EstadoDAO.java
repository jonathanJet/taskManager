/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.dao;

import com.taskmanager.model.Estado;
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
public class EstadoDAO {

    private Connection connection;

    public EstadoDAO() {
        connection = ConexionDB.conectar();
    }

    public void crearEstado(Estado estado) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("insert into tbl_estado(id_estado,nombre_estado) values ((select coalesce(max(id_estado),0) +1 from tbl_estado),?)");

            // Parameters start with 1
            preparedStatement.setString(1, estado.getEstado());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEstado(int estadoId) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("delete from tbl_estado where id_estado=?");

            // Parameters start with 1
            preparedStatement.setInt(1, estadoId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEstado(Estado estado) {
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("update tbl_estado set nombre_estado=?" +
                    "where id_estado=?");

            // Parameters start with 1
            preparedStatement.setString(1, estado.getEstado());
            preparedStatement.setInt(2, estado.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estado> traerEstados() {
        List<Estado> users = new ArrayList<Estado>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_estado");
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getInt("id_estado"));
                estado.setEstado(rs.getString("nombre_estado"));
               
                users.add(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Estado estadoXId(int estadoId) {
        Estado estado = new Estado();
        try {

            PreparedStatement preparedStatement = 
                    connection.prepareStatement("select * from tbl_estado where id_estado=?");

            preparedStatement.setInt(1, estadoId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                estado.setId(rs.getInt("id_estado"));
                estado.setEstado(rs.getString("nombre_estado"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }   
}
