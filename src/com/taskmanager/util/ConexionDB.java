/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author administrador
 */
public class ConexionDB {
    private static Connection connection = null;

    public static Connection conectar() {
        Connection conn = null;

        String password = "1234";
        String usuario = "kfigueroa";
        String instance = "SDPJ1";
        String database = "db_proj";
        
        try {

            String url = "jdbc:sqlserver://SERVER-PRUEBAS\\" + instance + ":1433;databaseName=" + database;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, usuario, password);
            
            if (conn != null) {
                    System.out.println("Conectado");
            }else{System.out.println("No Conectado");}

            /*Statement statement = conn.createStatement();
            ResultSet resultSet = null;
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT *  from tbl_usuario";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }*/
        } catch (Exception e) {
                System.out.println("No se pudo conectar a la base de datos");
                e.printStackTrace();
        }
        
        return conn;
    }
        
}
