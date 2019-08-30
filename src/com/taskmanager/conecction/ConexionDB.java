/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.conecction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author administrador
 */
public class ConexionDB {
     /*public static Connection conectar() {
        Connection con = null;

        String password = "";
        String usuario = "DESKTOP-MVTAJC8//Keylis";
      // String url = "jdbc:mysql://localhost:3306/ventas?user=" + usuario + "&password=" + password;
        
        String url =  "jdbc:sqlserver://DESKTOP-MVTAJC8//SQLEXPRESS.database.windows.net:1433;"
                        + "database=db_prueba;"
                        + "user=;" + usuario
                        + "password=;"  + password
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;"; 
                
        try {
                con = DriverManager.getConnection(url);
                if (con != null) {
                        System.out.println("Conectado");
                }
        } catch (SQLException e) {
                System.out.println("No se pudo conectar a la base de datos");
                e.printStackTrace();
        }
        return con;
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
      Connection con = null;

        String password = "";
        String usuario = "DESKTOP-MVTAJC8//Keylis";
      // String url = "jdbc:mysql://localhost:3306/ventas?user=" + usuario + "&password=" + password;
        
        /*String url =  "jdbc:sqlserver://DESKTOP-MVTAJC8//SQLEXPRESS:1433;"
                        + "database=db_prueba;"
                        + "user=;" + usuario
                        + "password=;"  + password
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;"; */
       //String url = "jdbc:sqlserver://DESKTOP-MVTAJC8;instanceName=SQLEXPRESS;databaseName=db_prueba";
       /*String url = "jdbc:sqlserver://DESKTOP-MVTAJC8\\SQLEXPRESS;databaseName=db_prueba;";

        try {

            String url = "jdbc:sqlserver://DESKTOP-MVTAJC8\\SQLEXPRESS:1433;databaseName=db_prueba";
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
Connection conn = DriverManager.getConnection(url,"sa","sa");
                if (conn != null) {
                        System.out.println("Conectado");
                }else{System.out.println("No Conectado");}
               /* Statement statement = con.createStatement();
                ResultSet resultSet = null;
                // Create and execute a SELECT SQL statement.
                String selectSql = "SELECT *  from cliente";
                resultSet = statement.executeQuery(selectSql);

                // Print results from select statement
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
                }
        } catch (SQLException e) {
                System.out.println("No se pudo conectar a la base de datos");
                e.printStackTrace();
        }
        
    }
       */ 
}
