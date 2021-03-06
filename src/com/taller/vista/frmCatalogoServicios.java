/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.vista;

import com.taller.dao.CatalogoServicioDao;
import com.taller.dao.OrdenTrabajoDetalleDao;
import com.taller.modelo.CatalogoServicio;
import com.taller.modelo.OrdenTrabajoDetalle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class frmCatalogoServicios extends javax.swing.JFrame {

    /**
     * Creates new form frmCatalogoServicios
     */
    public frmCatalogoServicios() {
        initComponents();
        txtServicioId.setVisible(false);
    }
    
    
    private int estadoRegistro = 0;
    private CatalogoServicio catalogoActual = null;
    
    
    private void limpiarCampos() {
        txtServicio.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtServicioId.setText("");
    }
    
    private void habilitarCampos(){
        txtServicio.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtServicioId.setEnabled(true);
        chHabilitado.setEnabled(true);
    }
    
    private void deshabilitarCampos(){
        txtServicio.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtServicioId.setEnabled(false);
        chHabilitado.setEnabled(false);
    }  
    
    private void buscarServicio(){
        CatalogoServicio c = new CatalogoServicio();
        CatalogoServicioDao cCat = new CatalogoServicioDao();
        
        c = cCat.obtenerServicioById(Integer.parseInt(txtBuscar.getText()));
        
        if (c != null){
            txtServicio.setText(c.getNombre());
            txtDescripcion.setText(c.getDetalle());
            txtPrecio.setText(c.getPrecio()+"");
            txtServicioId.setText(c.getIdCatalogoServicio()+"");           
        } else {
            JOptionPane.showMessageDialog(rootPane, "Servicio no encontrado");
            catalogoActual = null;
            limpiarCampos();
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

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnListado = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        chHabilitado = new javax.swing.JCheckBox();
        txtServicioId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("ID");

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnListado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/listado.png"))); // NOI18N
        btnListado.setText("Listado");
        btnListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListadoActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/search.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setMaximumSize(new java.awt.Dimension(129, 49));
        btnNuevo.setMinimumSize(new java.awt.Dimension(129, 49));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnListado, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Servicio*");

        txtServicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtServicio.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Descripcion");

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDescripcion.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Precio*");

        txtPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPrecio.setEnabled(false);

        chHabilitado.setBackground(new java.awt.Color(255, 255, 255));
        chHabilitado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chHabilitado.setText("Habilitado");
        chHabilitado.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chHabilitado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtServicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chHabilitado)
                .addGap(11, 11, 11))
        );

        txtServicioId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtServicioId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtServicioId, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(txtServicioId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoActionPerformed
        // TODO add your handling code here:
            diagRegistroCatalogoServicioLista l = new diagRegistroCatalogoServicioLista(this, true);                 
            l.btnSeleccionar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                
                    System.out.println("Id Servicio Seleccionado: " + l.obtenerServicioSeleccionado());
                    if (l.obtenerServicioSeleccionado() == 0){
                        JOptionPane.showMessageDialog(getParent(),
                                    "No se ha seleccionado ningun servicio de la lista",
                                    "Orden de Servicio",
                                    JOptionPane.WARNING_MESSAGE);
                    } else {
                        txtBuscar.setText(l.obtenerServicioSeleccionado()+"");
                        buscarServicio();
                        l.dispose();
                    }
                }
            });

            l.setVisible(true);  
    }//GEN-LAST:event_btnListadoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

                buscarServicio();
//                CatalogoServicioDao aDao = new CatalogoServicioDao();
//                a = aDao.obtenerAutomovilById(Integer.parseInt(txtBuscar.getText()));
//        
//                if (a != null){
//                       txtPlacas.setText(a.getPlacas());
//                       txtModelo.setText(a.getModelo()+"");
//                       txtMarca.setText(a.getMarca());
//                       txtColor.setText(a.getColor());
//                       btnEditar.setEnabled(true);
//                       autoActual = a;
//                    } else {
//                        JOptionPane.showMessageDialog(rootPane, "Automovil no encontrado");
//                        autoActual = null;
//                        limpiarCampos();
//                    }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        /* Si esta en modo nuevo cambia a modo edición (1) de lo contrario de modo edición cambia a modo nuevo (0)*/
                if (estadoRegistro == 0) {
                        estadoRegistro = 1;
                    } else{
                        estadoRegistro = 0;
                    }
        
                if (estadoRegistro == 1 ){
                        habilitarCampos();
                        limpiarCampos();
                        txtBuscar.setEnabled(false);
                        txtBuscar.setText("");
                        btnEditar.setEnabled(false);
                        btnGuardar.setEnabled(true);
                        btnNuevo.setText("Cancelar");
                        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/cancelar.png"))); // NOI18N
//                        clienteActual = null;
                    } else if (estadoRegistro == 3 ) {
                        habilitarCampos();
                        txtBuscar.setEnabled(true);
                        btnEditar.setEnabled(true);
                        btnGuardar.setEnabled(false);
                        btnNuevo.setText("Nuevo");
                        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/nuevo.png"))); // NOI18N
                    } else {
                        deshabilitarCampos();
                        txtBuscar.setEnabled(true);
                        //txtBuscar.setText("");
                        btnEditar.setEnabled(true);
                        btnGuardar.setEnabled(false);
                        btnNuevo.setText("Nuevo");
                        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/nuevo.png"))); // NOI18N
                        estadoRegistro = 0;
                    }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:

        //Verificar si el registro existe en BD antes de poder editar,
        //de lo contrario se da de alta uno nuevo

                if(catalogoActual == null ) {
                        JOptionPane.showMessageDialog(getParent(), "No se puede actualizar un catalogo que no existe");
                    } else {
            
                        if(catalogoActual.getIdCatalogoServicio()== 0){
                                JOptionPane.showMessageDialog(getParent(), "No se puede actualizar un catalogo que no existe");
                            } else {
                
                                if (estadoRegistro == 0) {
                                        estadoRegistro = 3;
                                    } else{
                                        estadoRegistro = 0;
                                    }
                
                                if (estadoRegistro == 3 ){
                                        habilitarCampos();
                                        txtBuscar.setEnabled(false);
                                        btnEditar.setEnabled(false);
                                        btnGuardar.setEnabled(true);
                                        btnNuevo.setText("Cancelar");
                                    } else {
                                        /* deshabilitarCampos();
                                        txtBuscar.setEnabled(true);
                                        txtBuscar.setText("");
                                        btnEditar.setEnabled(true);
                                        btnGuardar.setEnabled(false);
                                        btnNuevo.setText("Nuevo");*/
                                    }
                            }
                    }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        /* Validar que solo datos básicos estén llenos */
                if ( txtPrecio.getText().equals("") && txtServicio.getText().equals("")){
                        JOptionPane.showMessageDialog(getParent(),
                                "Se deben llenar los datos marcados en asterisco como mínimo",
                                "Registro de Clientes",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        CatalogoServicio c = new CatalogoServicio();                        
                        if (catalogoActual != null){
                                CatalogoServicioDao cDao = new CatalogoServicioDao();                                
                                c = catalogoActual; 
                                
                                c.setNombre(txtServicio.getText());
                                c.setDetalle(txtDescripcion.getText());
                                try{
                                    c.setPrecio(Double.parseDouble(txtPrecio.getText()));
                                }catch(Exception ex){
                                    c.setPrecio(0);
;
                                }
                                
                                if(chHabilitado.isSelected()){
                                    c.setActivo("1");
                                } else {
                                    c.setActivo("0");
                                }
                                
                                
                                if ( cDao.actualizarCatalogoServicio(c)) {
                                        catalogoActual = c;
                                        deshabilitarCampos();
                                        txtBuscar.setEnabled(true);
                                        btnGuardar.setEnabled(false);
                                        btnNuevo.setEnabled(true);
                                        btnEditar.setEnabled(true);
                                        btnNuevo.setText("Nuevo");
                                        estadoRegistro = 0; // Sale de modo edición
                                    } else{
                                        JOptionPane.showMessageDialog(getParent(),
                                                "Ha ocurrido un error al registrar el cliente",
                                                "Registro de Clientes",
                                                JOptionPane.WARNING_MESSAGE);
                                    }
                            } else{
                            CatalogoServicioDao cDao = new CatalogoServicioDao();  
                                c.setIdCatalogoServicio(0);
                                c.setNombre(txtServicio.getText());
                                c.setDetalle(txtDescripcion.getText());
                                try{
                                    c.setPrecio(Double.parseDouble(txtPrecio.getText()));
                                }catch(Exception ex){
                                    c.setPrecio(0);
                                    ex.printStackTrace();;
                                }
                                
                                if(chHabilitado.isSelected()){
                                    c.setActivo("1");
                                } else {
                                    c.setActivo("0");
                                }
                                
                                c = cDao.registrarCatalogoServicio(c);
                
                                if (  c.getIdCatalogoServicio()!= 0){
                                        catalogoActual = c;
                                        deshabilitarCampos();
                                        txtBuscar.setEnabled(true);
                                        btnGuardar.setEnabled(false);
                                        btnNuevo.setEnabled(true);
                                        btnEditar.setEnabled(true);
                                        btnNuevo.setText("Nuevo");
                                        estadoRegistro = 0; // Sale de modo edición
                                    } else{
                    
                                    }
                            }
            
                    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCatalogoServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCatalogoServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCatalogoServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCatalogoServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCatalogoServicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chHabilitado;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtServicioId;
    // End of variables declaration//GEN-END:variables
}
