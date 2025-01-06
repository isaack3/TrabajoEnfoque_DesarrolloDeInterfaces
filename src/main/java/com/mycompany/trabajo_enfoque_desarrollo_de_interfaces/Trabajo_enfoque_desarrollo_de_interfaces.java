/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabajo_enfoque_desarrollo_de_interfaces;

/**
 *
 * @author sebastiancamposrojas
 */
public class Trabajo_enfoque_desarrollo_de_interfaces {

    public static void main(String[] args) {
        System.out.println("Bienvenidos a SmartOcupation.");
        
        /**
         * Genero instancia de la nueva ventana a querer mostrar.
         */
        SmartOcupationApp app = new SmartOcupationApp();
        app.setDefaultCloseOperation(SmartOcupationApp.EXIT_ON_CLOSE);
        
        /**
         * Aquí entrego medidas y centro las ventanas en la pantalla.
         */
        app.setSize(767, 600);
        app.setLocationRelativeTo(null);
        
        /**
         * Aquí le obligo a mostrar la ventana.
         */
        app.setVisible(true);
        
        /**
         * Aquí creo la base de datos y la tabla de viviendas, en caso de no existir.
         */
        DatabaseConnection db = new DatabaseConnection();
        db.createDatabase(DatabaseConnection.DBNAME_SMARTOCUPATION);
        db.createTableDatabase(DatabaseConnection.DBNAME_SMARTOCUPATION, DatabaseConnection.TBNAME_VIVIENDAS);
    }
}
