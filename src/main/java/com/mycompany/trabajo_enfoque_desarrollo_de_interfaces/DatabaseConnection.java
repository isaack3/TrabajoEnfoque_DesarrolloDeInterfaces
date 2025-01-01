/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajo_enfoque_desarrollo_de_interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sebastiancamposrojas
 */
public class DatabaseConnection {
    /**
     * Variables constantes.
     */
    public static final String DBNAME_SMARTOCUPATION = "smart_ocupation";
    public static final String TBNAME_VIVIENDAS = "viviendas";
    
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    public static final String DB_STRING_CONNECTION = "jdbc:mysql://localhost:3306";
    
    /**
     * Metodo que crea la conexión con la base de datos mysql.
     * @return Connection
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_STRING_CONNECTION, DB_USERNAME, DB_PASSWORD);
    }
    
    /**
     * Método para la creación de la bases de datos.
     * @param dbname 
     */
    public static void createDatabase(String dbname){
        Connection conn = null;
        Statement stmt = null;
        
        /**
         * Se encapsula el proceso con try/catch para evitar errores no controlados.
         */
        try {
            String sql = "CREATE DATABASE IF NOT EXISTS " + dbname;
            conn = getConnection();
            stmt = conn.createStatement();
            
            /**
             * Ejecutado de la sentencia sql.
             */
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            
            /**
             * Con finally me aseguro que pasará siempre por acá 
             * y que cerrará la conexión y la declaración de ejecución del SQL.
             */
        } finally {
            /**
            * Se encapsula el proceso con try/catch para evitar errores no controlados.
            */
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión de bases de datos: " + e.getMessage());
            }
        }
    }
    
    /**
     * Método para la creación de tabla.
     * @param dbname
     * @param tbname 
     */
    public static void createTableDatabase(String dbname, String tbname){
        Connection conn = null;
        Statement stmt = null;
        
        /**
         * Se encapsula el proceso con try/catch para evitar errores no controlados.
         */
        try {
            /**
             * Se crea tabla en base de datos, 
             * agregando una columna ID primary key autoincrementable.
             */
            String sql = "CREATE TABLE IF NOT EXISTS " + tbname + " (" 
                    + "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY," 
                    + "NUMERO_EXPEDIENTE VARCHAR(100) DEFAULT NULL," 
                    + "FECHA_ENTRADA DATE DEFAULT NULL," 
                    + "TIEMPO_ESTIMADO_ALQUILER VARCHAR(50) DEFAULT NULL," 
                    + "CLIENTE_NOMBRE VARCHAR(100) DEFAULT NULL," 
                    + "CLIENTE_APELLIDOS VARCHAR(100) DEFAULT NULL," 
                    + "CLIENTE_TELEFONO VARCHAR(15) DEFAULT NULL," 
                    + "FACTURACION_NIF_DNI VARCHAR(20) DEFAULT NULL," 
                    + "FACTURACION_TIPO_EMPRESA VARCHAR(50) DEFAULT NULL," 
                    + "FACTURACION_NOMBRE_EMPRESA VARCHAR(100) DEFAULT NULL," 
                    + "VIVIENDA_IDENTIFICADOR VARCHAR(50) DEFAULT NULL," 
                    + "VIVIENDA_DIRECCION VARCHAR(200) DEFAULT NULL," 
                    + "VIVIENDA_CODIGO_POSTAL VARCHAR(10) DEFAULT NULL," 
                    + "VIVIENDA_METROS INT DEFAULT NULL," 
                    + "VIVIENDA_NUMERO_HABITACIONES INT DEFAULT NULL," 
                    + "VIVIENDA_NUMERO_BANOS INT DEFAULT NULL," 
                    + "VIVIENDA_CODIGO_REFERENCIA VARCHAR(50) DEFAULT NULL," 
                    + "VIVIENDA_PRECIO DECIMAL(10, 2) DEFAULT NULL," 
                    + "VIVIENDA_OBSERVACIONES TEXT DEFAULT NULL" 
                    + ");";
            
            conn = getConnection();
            stmt = conn.createStatement();
            
            /**
             * Seleccionado de base de datos.
             */
            selectDatabase(dbname, conn, stmt);
            
            /**
             * Ejecutado de la sentencia sql.
             */
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            
            /**
             * Con finally me aseguro que pasará siempre por acá 
             * y que cerrará la conexión y la declaración de ejecución del SQL.
             */
        } finally {
            /**
            * Se encapsula el proceso con try/catch para evitar errores no controlados.
            */
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión de bases de datos: " + e.getMessage());
            }
        }
    }
    
    /**
     * Método para seleccionar la base de datos a utilizar.
     * @param dbname 
     * @param conn 
     * @param stmt
     */
    public static void selectDatabase(String dbname, Connection conn, Statement stmt){
        /**
         * Se encapsula el proceso con try/catch para evitar errores no controlados.
         */
        try {
            String sql = "USE " + dbname;
            
            /**
             * Ejecutado de la sentencia sql.
             */
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Método para el insertado de datos en la tabla de bases de datos previamente creada.
     * @param dbname
     * @param tbname
     */
    public static void insertValuesDatabase(String dbname, String tbname) {
        
    }
}
