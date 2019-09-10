/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.dao;

import com.taskmanager.util.ConexionDB;
import com.taskmanager.model.Usuario;
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
public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO() {
        connection = ConexionDB.conectar();
    }

    public void crearUsuario(Usuario user) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("insert into tbl_usuario(id_usuario, nombre,usuario,contrasenia,activo) values ((select coalesce(max(id_usuario),0) +1 from tbl_usuario), ?, ?, ?,? )");

            // Parameters start with 1
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.setString(2, user.getUsuario());
            preparedStatement.setString(3, user.getContrasenia());
            //preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setInt(4, user.getActivo());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void addUserRol(Usuario user) {
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("insert into tbl_usuario_rol(id, id_usuario,id_rol) values ((select coalesce(max(id),0) +1 from tbl_usuario_rol),(select (max(id_usuario)) from tbl_usuario), ?)");

            // Parameters start with 1
            preparedStatement.setInt(1, user.getIdRol());

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
                    connection.prepareStatement("select a.id_rol,b.* from tbl_usuario_rol a, tbl_usuario b where a.id_usuario =b.id_usuario and b.id_usuario=?");

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
    }
    
    public String getUserRol(Integer ID) {
        String rol ="";
        try {
            PreparedStatement preparedStatement = 
            connection.prepareStatement("select b.nombre_rol rol from tbl_usuario_rol a, tbl_roles b where a.id_rol = b.id_rol and a.id_usuario= ?");

            // Parameters start with 1
            preparedStatement.setInt(1, ID);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rol = rs.getString("rol");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }
    
    public Usuario login(Usuario user) {
        Usuario userLogin = new Usuario();
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = 
                    connection.prepareStatement("select c.*,b.* from tbl_usuario_rol a, tbl_usuario b, tbl_roles c where c.id_rol=a.id_rol and a.id_usuario =b.id_usuario and b.usuario=? and b.contrasenia=?");

            preparedStatement.setString(1, user.getUsuario());
            preparedStatement.setString(2, user.getContrasenia());
            
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                userLogin.setId(rs.getInt("id_usuario"));
                userLogin.setNombre(rs.getString("nombre"));
                userLogin.setUsuario(rs.getString("usuario"));
                userLogin.setContrasenia(rs.getString("contrasenia"));
                userLogin.setActivo(rs.getInt("activo"));
                userLogin.setIdRol(rs.getInt("id_rol"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
        return userLogin;
    }
    
     public List<Usuario> traerResponsables() {
        List<Usuario> users = new ArrayList<Usuario>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select a.id_usuario, a.nombre from tbl_usuario a, tbl_usuario_rol b where a.id_usuario = b.id_usuario and b.id_rol = 1");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
               
                users.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
