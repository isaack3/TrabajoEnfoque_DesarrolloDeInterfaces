/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajo_enfoque_desarrollo_de_interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

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
    public void createDatabase(String dbname){
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
    public void createTableDatabase(String dbname, String tbname){
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
                    + "TIEMPO_ESTIMADO_ALQUILER INT DEFAULT NULL," 
                    + "CLIENTE_NOMBRE VARCHAR(100) DEFAULT NULL," 
                    + "CLIENTE_APELLIDOS VARCHAR(100) DEFAULT NULL," 
                    + "CLIENTE_TELEFONO VARCHAR(15) DEFAULT NULL," 
                    + "FACTURACION_NIF_DNI VARCHAR(20) DEFAULT NULL," 
                    + "FACTURACION_TIPO_EMPRESA VARCHAR(50) DEFAULT NULL," 
                    + "FACTURACION_NOMBRE_EMPRESA VARCHAR(100) DEFAULT NULL," 
                    + "VIVIENDA_IDENTIFICADOR VARCHAR(50) DEFAULT NULL," 
                    + "VIVIENDA_DIRECCION VARCHAR(200) DEFAULT NULL," 
                    + "VIVIENDA_CODIGO_POSTAL INT DEFAULT NULL," 
                    + "VIVIENDA_METROS DECIMAL(10, 2) DEFAULT NULL," 
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
    public void selectDatabase(String dbname, Connection conn, Statement stmt){
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
     * @param data
     * @return 
     * @throws java.text.ParseException
     */
    public int insertValuesDatabase(String dbname, String tbname, Object[] data) throws ParseException {
        Connection conn = null;
        Statement stmt = null;
        int success;
        
        /**
         * Se encapsula el proceso con try/catch para evitar errores no controlados.
         */
        try {
            /**
             * Se crea la estructura de insertado de datos.
             */
            String sql = "INSERT INTO " + tbname + "("
                    + "ID, "
                    + "NUMERO_EXPEDIENTE, "
                    + "FECHA_ENTRADA, "
                    + "TIEMPO_ESTIMADO_ALQUILER, "
                    + "CLIENTE_NOMBRE, "
                    + "CLIENTE_APELLIDOS, "
                    + "CLIENTE_TELEFONO, "
                    + "FACTURACION_NIF_DNI, "
                    + "FACTURACION_TIPO_EMPRESA, "
                    + "FACTURACION_NOMBRE_EMPRESA, "
                    + "VIVIENDA_IDENTIFICADOR, "
                    + "VIVIENDA_DIRECCION, "
                    + "VIVIENDA_CODIGO_POSTAL, "
                    + "VIVIENDA_METROS, "
                    + "VIVIENDA_NUMERO_HABITACIONES, "
                    + "VIVIENDA_NUMERO_BANOS, "
                    + "VIVIENDA_CODIGO_REFERENCIA, "
                    + "VIVIENDA_PRECIO, "
                    + "VIVIENDA_OBSERVACIONES"
                    + ") "
                    + "VALUES (DEFAULT, ";
            
            
            /**
             * Aquí creo un conjunto de valores para realizar comprobaciones de valores numericos.
             */
            Set<Integer> valores_numericos = new HashSet<>();
            valores_numericos.add(2);
            valores_numericos.add(11);
            valores_numericos.add(12);
            valores_numericos.add(13);
            valores_numericos.add(14);
            valores_numericos.add(16);
            
            /**
             * Bucle para definir los valores de la sentencia INSERT.
             */
            for (int x = 0; x < data.length; x++) {
                if (x == data.length - 1) {
                    sql += data[x] != null ? "'" + data[x] + "'" : "NULL";
                } else {
                    if (valores_numericos.contains(x)) {
                        sql += data[x] != null ? data[x] + "," : "NULL,";
                    } else {
                        sql += data[x] != null ? "'" + data[x] + "'," : "NULL,";
                    }
                }
            }
            
            sql += ");";
            
            System.out.println(sql);
            
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
            System.out.println("Datos en: " + dbname + "." + tbname);
            success = 1;
        } catch (SQLException e) {
            success = 0;
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
        
        return success;
    }
    
    /**
     * Método para el mostrado de datos en el componente de tabla.
     * @param dbname
     * @param tbname
     * @param filters
     * @return 
     * @throws java.sql.SQLException 
     */
    public ResultSet loadDataToTable(String dbname, String tbname, String filters) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        /**
         * Seleccionado de base de datos.
         */
        selectDatabase(dbname, conn, stmt);

        /**
         * Se define la consulta de datos.
         */
        String sql = "SELECT * FROM " + dbname + "." + tbname + " WHERE true " + filters;
        System.out.println(sql);

        /**
         * Ejecutado de la sentencia sql.
         */
        return stmt.executeQuery(sql);
    }
}
