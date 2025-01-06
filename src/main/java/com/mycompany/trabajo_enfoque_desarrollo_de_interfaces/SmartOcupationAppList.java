/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.trabajo_enfoque_desarrollo_de_interfaces;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebastiancamposrojas
 */
public class SmartOcupationAppList extends javax.swing.JFrame {
    /**
     * Aquí declaro el JDateChooser_inicio, JDateChooser_fin, table y scrollPane como atributo de clase.
     */
    public JDateChooser dateChooser_inicio;
    public JDateChooser dateChooser_fin;
    public JTable table;
    public JScrollPane scrollPane;
    public DefaultTableModel model;
    public String filters = "";

    /**
     * Creates new form SmartOcupationAppMenu
     */
    public SmartOcupationAppList() {
        setLayout(new BorderLayout());
        setSize(767, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
        /**
         * Aquí agrego componente JDateChooser dateChooser_inicio.
         */
        dateChooser_inicio = new JDateChooser();
        dateChooser_inicio.setBounds(189, 44, 150, 30);
        dateChooser_inicio.setDate(null);
        dateChooser_inicio.setDateFormatString("yyyy-MM-dd");
        add(dateChooser_inicio);
        
        /**
         * Aquí agrego componente JDateChooser dateChooser_fin.
         */
        dateChooser_fin = new JDateChooser();
        dateChooser_fin.setBounds(550, 44, 150, 30);
        dateChooser_fin.setDate(null);
        dateChooser_fin.setDateFormatString("yyyy-MM-dd");
        add(dateChooser_fin);
        
        this.getTableViviendas(filters);

        /**
         * Aquí desactivo el LayoutManager para usar coordenadas para posicionar elementos.
         */
        setLayout(null);
        
        initComponents();
    }
    
    /**
     * Cargamos datos desde base de datos a la tabla para visualizar informacion.
     * @param filters
     */
    public final void getTableViviendas(String filters){
        try {
            model = new DefaultTableModel();
            table = new JTable(model);
            
            /**
             * Ahora configuro el nombre de las columnas.
             */
            model.addColumn("Expediente");
            model.addColumn("Fecha Entrada");
            model.addColumn("Tiempo Alquiler");
            model.addColumn("Cliente");
            model.addColumn("ID Vivienda");
            model.addColumn("Dirección");
            model.addColumn("Cod. Postal");
            model.addColumn("Mts");
            model.addColumn("Habitaciones");
            model.addColumn("Baños");
            model.addColumn("Cod. Ref. Vivienda");
            model.addColumn("Precio");
            
            /**
             * Obtengo los datos desde base de datos.
             */
            DatabaseConnection db = new DatabaseConnection();
            ResultSet rs = db.loadDataToTable(DatabaseConnection.DBNAME_SMARTOCUPATION, DatabaseConnection.TBNAME_VIVIENDAS, filters);
            
            /**
             * Limpio el modelo antes de agregar nuevas filas.
             */
            model.setRowCount(0);
            
            /**
             * Recorrer el ResultSet y agregar los datos al modelo de tabla.
             */
            while (rs.next()) {
                // System.out.println("Fila: ID=" + rs.getInt("ID") + ", NUMERO_EXPEDIENTE=" + rs.getString("NUMERO_EXPEDIENTE"));
                Object[] row = new Object[12];
                row[0] = rs.getString("NUMERO_EXPEDIENTE");
                row[1] = rs.getString("FECHA_ENTRADA");
                row[2] = rs.getInt("TIEMPO_ESTIMADO_ALQUILER");
                row[3] = rs.getString("CLIENTE_NOMBRE") + " " + rs.getString("CLIENTE_APELLIDOS");
                row[4] = rs.getString("VIVIENDA_IDENTIFICADOR");
                row[5] = rs.getString("VIVIENDA_DIRECCION");
                row[6] = rs.getInt("VIVIENDA_CODIGO_POSTAL");
                row[7] = rs.getDouble("VIVIENDA_METROS");
                row[8] = rs.getInt("VIVIENDA_NUMERO_HABITACIONES");
                row[9] = rs.getInt("VIVIENDA_NUMERO_BANOS");
                row[10] = rs.getString("VIVIENDA_CODIGO_REFERENCIA");
                row[11] = rs.getDouble("VIVIENDA_PRECIO");
                
                model.addRow(row);
            }
            
            /**
             * Asignación del modelo a la tabla.
             */
            table.setModel(model);
            
            /**
             * Establecezco el ancho de las columnas (en pixeles).
             */
            table.getColumnModel().getColumn(0).setPreferredWidth(300); // Núm. Expediente
            table.getColumnModel().getColumn(1).setPreferredWidth(300); // Fecha Entrada
            table.getColumnModel().getColumn(2).setPreferredWidth(300); // Tiempo Alquiler
            table.getColumnModel().getColumn(3).setPreferredWidth(300); // Nombre Cliente
            table.getColumnModel().getColumn(4).setPreferredWidth(300); // ID Vivienda
            table.getColumnModel().getColumn(5).setPreferredWidth(300); // Dirección
            table.getColumnModel().getColumn(6).setPreferredWidth(300); // Código postal
            table.getColumnModel().getColumn(7).setPreferredWidth(300); // Mts. Vivienda
            table.getColumnModel().getColumn(8).setPreferredWidth(300); // Hab. Vivienda
            table.getColumnModel().getColumn(9).setPreferredWidth(300); // Baños Vivienda
            table.getColumnModel().getColumn(10).setPreferredWidth(300); // Ref. Cod. Vivienda
            table.getColumnModel().getColumn(11).setPreferredWidth(300); // Precio
            
            /**
             * Aquí agrego un scroll para poder tenerlo preparado cuando el listado crezca.
             */
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(11, 125, 745, 400);
            // // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            // // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            getContentPane().add(scrollPane, BorderLayout.CENTER);
            
            /**
             * Revalidación y repintado.
             */
            getContentPane().revalidate();
            getContentPane().repaint();
            
            System.out.println("Filas: " + model.getRowCount());
            System.out.println("Columnas: " + model.getColumnCount());
        } catch (SQLException ex) {
            Logger.getLogger(SmartOcupationAppList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SmartOcupation");

        jButton3.setText("Generar CSV");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Atrás");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cerrar Programa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(35, 35, 35))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jButton2.setText("Filtrar Datos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha Inicio Alquiler:");

        jLabel7.setText("Fecha Fin Alquiler:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(252, 252, 252)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 396, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /**
         * Genero informe a exportar.
         */
        try {
            Reports.generate(filters, DatabaseConnection.TBNAME_VIVIENDAS);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SmartOcupationAppList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        /**
         * Aquí obligo a ocultar la ventana actual.
         */
        this.dispose();
        
        /**
         * Genero instancia de la nueva ventana a querer mostrar.
         */
        SmartOcupationApp app = new SmartOcupationApp();
        
        /**
         * Aquí centro las ventanas en la pantalla.
         */
        app.setLocationRelativeTo(null);
        
        /**
         * Aquí le obligo a mostrar la ventana.
         */
        app.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Date fecha_ini = dateChooser_inicio.getDate() != null ? dateChooser_inicio.getDate() : null; 
        Date fecha_fin = dateChooser_fin.getDate() != null ? dateChooser_fin.getDate() : null;
        
        if(fecha_ini != null) {
            SimpleDateFormat sdf_i = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate_ini = sdf_i.format(fecha_ini);
            
            filters += " AND FECHA_ENTRADA >= '" + formattedDate_ini + "'";
        }
        
        if(fecha_fin != null) {
            SimpleDateFormat sdf_f = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate_fin = sdf_f.format(fecha_fin);
            
            filters += " AND FECHA_ENTRADA <= '" + formattedDate_fin + "'";
        }
        
        this.getTableViviendas(filters);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
