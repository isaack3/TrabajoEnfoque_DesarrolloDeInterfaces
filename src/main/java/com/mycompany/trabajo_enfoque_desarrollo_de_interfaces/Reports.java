/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajo_enfoque_desarrollo_de_interfaces;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sebastiancamposrojas
 */
public class Reports {
    public static void generate(String filters, String name) throws SQLException, IOException{
        /**
         * Declaramos variables de fechas para luego utilizarlas en el nombre del fichero.
         */
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String filename = "reporte_" + name + "_" + now.format(formatter) + ".csv";
        
        DatabaseConnection db = new DatabaseConnection();
        ResultSet rs = db.loadDataToTable(DatabaseConnection.DBNAME_SMARTOCUPATION, DatabaseConnection.TBNAME_VIVIENDAS, filters);
        
        /**
         * Aquí utilizo componente para escribir ficheros de salida.
         */
        FileWriter csvWriter = new FileWriter(filename);
        
        /**
         * Obtengo el total de columnas, 
         * para luego crear los titulos de las columnas del fichero de salida.
         */
        int columnCount = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            csvWriter.append(rs.getMetaData().getColumnName(i));
            if (i < columnCount) {
                csvWriter.append(",");
            }
        }
        csvWriter.append("\n");
        
        /**
         * Aquí itero por cada fila de datos que tengo almacenadas en bbdd, 
         * para rellenar el fichero de salida.
         */
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                csvWriter.append(rs.getString(i));
                if (i < columnCount) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");
        }
        
        System.out.println("CSV Exportado con éxito: " + filename);
    }
}
