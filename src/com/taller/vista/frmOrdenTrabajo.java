/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.vista;

import com.taller.dao.AutomovilDao;
import com.taller.dao.ClienteDao;
import com.taller.dao.EmpleadoDao;
import com.taller.dao.OrdenTrabajoDao;
import com.taller.modelo.Automovil;
import com.taller.modelo.CatalogoServicio;
import com.taller.dao.CatalogoServicioDao;
import com.taller.dao.OrdenTrabajoDetalleDao;
import com.taller.modelo.Cliente;
import com.taller.modelo.Empleado;
import com.taller.modelo.OrdenTrabajo;
import com.taller.modelo.OrdenTrabajoDetalle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class frmOrdenTrabajo extends javax.swing.JFrame {

    /**
     * Creates new form frmOrdenTrabajo
     */
    public frmOrdenTrabajo() {
        initComponents();        
//        llenarCombos();
        tblDetalleServicios.getColumnModel().getColumn(0).setWidth(0);
        tblDetalleServicios.getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicios.getColumnModel().getColumn(0).setMaxWidth(0);
        getContentPane().setBackground(Color.WHITE);
        
        txtClienteId.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                limpiarCampo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                limpiarCampo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                limpiarCampo();
            }
            
            public void limpiarCampo(){
                txtVehiculoId.setText("");
            }
        });
    }
    
    private OrdenTrabajo ordenTrabajoActual;
    private ArrayList<OrdenTrabajoDetalle> listaDetalles;
    private String[] encabezadoTabla = {"ID Servicio","Servicio","Detalle","Precio"};
    private DefaultTableModel model;
    private int estadoRegistro;
    
//    private void llenarCombos(){
//        cmbEstado.addItem("N|Nuevo");
//        cmbEstado.addItem("P|Proceso");
//        cmbEstado.addItem("F|Finalizado");
//    }
//    
    private void buscarServicio(){
        ordenTrabajoActual = null;
        model = new DefaultTableModel(encabezadoTabla, 0);
        OrdenTrabajo o = new OrdenTrabajo();
        OrdenTrabajoDao oDao = new OrdenTrabajoDao();
        Cliente c = new Cliente();
        ClienteDao cDao = new ClienteDao();
        Automovil a = new Automovil();
        AutomovilDao aDao = new AutomovilDao();
        Empleado e = new Empleado();
        EmpleadoDao eDao = new EmpleadoDao();
        
        if (txtBuscar.getText().equals(null) || txtBuscar.getText().equals("")){  
            limpiarCampos();
            JOptionPane.showMessageDialog(rootPane, "Se necesita escribir un id");
          } else{
              try{
                  o = oDao.obtenerOrdenTrabajoById(Integer.parseInt(txtBuscar.getText()));
              }catch(NumberFormatException ex){
                  o.setIdCliente(0);
                  JOptionPane.showMessageDialog(rootPane, "Texto a buscar debe ser entero, detalles de error " + ex.getMessage());
              }

            if (o != null){
                txtVehiculoId.setText(o.getIdOrdenTrabajo()+"");
                txtFolio.setText(o.getFolio());
                txtVehiculoId.setText(o.getIdOrdenTrabajo()+"");
                txtFechaRegistro.setDate(o.getFechaRegistro());
                txtFechaTerminado.setDate(o.getFechaCompletado());

                txtClienteId.setText(o.getIdCliente()+"");            
                c = cDao.obtenerClienteById(o.getIdCliente());
                lblNombreCliente.setText(c.getNombreCompleto());
                lblRfc.setText(c.getRfc());

                txtVehiculoId.setText(o.getIdAuto()+"");
                a = aDao.obtenerAutomovilById(o.getIdAuto());
                lblModelo.setText(a.getModelo());
                lblLinea.setText(a.getLinea());
                lblAnio.setText(a.getAnio()+"");

                txtMecanicoId.setText(o.getIdEmpleadoAsignado()+"");
                e = eDao.obtenerEmpleadoById(o.getIdEmpleadoAsignado());
                lblNombreMecanico.setText(e.getNombre() + " " + e.getApePat() + " " + e.getApeMat()); 
                
                if(o.getEstado().equals("N")){
                    txtEstado.setText("NUEVO");
                } else if(o.getEstado().equals("P")){
                    txtEstado.setText("PROCESO");
                } else if(o.getEstado().equals("F")){
                    txtEstado.setText("FINALIZADO");
                }
                
                ordenTrabajoActual = o;
                
                // Cargar Servicios del la Orden de Trabajo
                OrdenTrabajoDetalleDao oDtao = new OrdenTrabajoDetalleDao();
                CatalogoServicio cat = new CatalogoServicio();
                CatalogoServicioDao catDao;
                listaDetalles = oDtao.obtenerDetalleFromOrdenId(ordenTrabajoActual.getIdOrdenTrabajo());
                
                for(OrdenTrabajoDetalle ox: listaDetalles){
                    Vector v = new Vector();
                    v.add(ox.getIdOrdenTrabajoDetalle());
                    catDao = new CatalogoServicioDao();
                    cat = catDao.obtenerServicioById(ox.getIdCatalogoServicio());
                    v.add(cat.getNombre());
                    v.add(cat.getDetalle());
                    v.add(cat.getPrecio());
                    model.addRow(v);
                }
                
                tblDetalleServicios.setModel(model);
                tblDetalleServicios.getColumnModel().getColumn(0).setWidth(0);
                tblDetalleServicios.getColumnModel().getColumn(0).setMinWidth(0);
                tblDetalleServicios.getColumnModel().getColumn(0).setMaxWidth(0);

                JOptionPane.showMessageDialog(rootPane, "Orden de Servicio cargada");
            } else{
                JOptionPane.showMessageDialog(rootPane, "Orden de Servicio no encontrada");
                ordenTrabajoActual = null;
                limpiarCampos();
            }
        }
    }
    
    private void limpiarCampos(){
        txtNoOrdenTrabajo.setText("");
        txtClienteId.setText("");
        txtMecanicoId.setText("");
        lblNombreCliente.setText("");
        lblRfc.setText("");
        lblModelo.setText("");
        lblLinea.setText("");
        lblAnio.setText("");
        lblNombreMecanico.setText("");
        txtFechaRegistro.setDate(null);
        txtFechaTerminado.setDate(null);
        txtFolio.setText("");
//        txtFacturaId.setText("");
        model = new DefaultTableModel(encabezadoTabla, 0);
        tblDetalleServicios.setModel(model);
    }
    
    private void habilitarCampos(){
        txtFechaRegistro.setEnabled(true);
//        txtEstado.setEnabled(true);
        btnListaCliente.setEnabled(true);
        btnListaEmpleado.setEnabled(true);
        btnListaVehiculo.setEnabled(true);
        //txtFechaTerminado.setEnabled(true);
    }
    
    private void deshabilitarCampos(){
        txtFechaRegistro.setEnabled(false);
//        txtEstado.setEnabled(false);
        btnListaCliente.setEnabled(false);
        btnListaEmpleado.setEnabled(false);
        btnListaVehiculo.setEnabled(false);       
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
        btnListado = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFechaRegistro = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtFechaTerminado = new com.toedter.calendar.JDateChooser();
        txtNoOrdenTrabajo = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnListaCliente = new javax.swing.JButton();
        lblNombreMecanico = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnListaEmpleado = new javax.swing.JButton();
        lblNombreCliente = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnListaVehiculo = new javax.swing.JButton();
        lblModelo = new javax.swing.JLabel();
        txtVehiculoId = new javax.swing.JTextField();
        txtClienteId = new javax.swing.JTextField();
        txtMecanicoId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lblRfc = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblLinea = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblAnio = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleServicios = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        lblTotalServicios = new javax.swing.JLabel();
        btnAgregarServicio = new javax.swing.JButton();
        btnEliminarServicio = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnVistaPreliminar = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        lblTotalPrecio = new javax.swing.JLabel();
        btnProcesar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordenes de Trabajo");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("ID");

        btnListado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/listado.png"))); // NOI18N
        btnListado.setText("Listado");
        btnListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListadoActionPerformed(evt);
            }
        });

        btnBuscar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/search.png"))); // NOI18N
        btnBuscar1.setText("Buscar");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/order.png"))); // NOI18N
        btnNuevo.setText("Crear");
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

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(33, 33, 33)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar1)
                .addGap(18, 18, 18)
                .addComponent(btnListado)
                .addGap(17, 17, 17)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListado, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator1)
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Orden de Trabajo");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("No. Orden");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Fecha Registro*");

        txtFechaRegistro.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Folio");

        txtFolio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtFolio.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Estado");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Fecha Terminado");

        txtFechaTerminado.setEnabled(false);

        txtNoOrdenTrabajo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNoOrdenTrabajo.setEnabled(false);

        txtEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtEstado.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNoOrdenTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFechaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(txtFechaTerminado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77)
                .addComponent(jLabel8)
                .addGap(33, 33, 33)
                .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoOrdenTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaTerminado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Datos de Asignación y Detalles");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Cliente*");

        btnListaCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnListaCliente.setText("...");
        btnListaCliente.setEnabled(false);
        btnListaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaClienteActionPerformed(evt);
            }
        });

        lblNombreMecanico.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNombreMecanico.setForeground(new java.awt.Color(0, 51, 255));
        lblNombreMecanico.setText("lblNombreMecánico");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Mecánico*");

        btnListaEmpleado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnListaEmpleado.setText("...");
        btnListaEmpleado.setEnabled(false);
        btnListaEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaEmpleadoActionPerformed(evt);
            }
        });

        lblNombreCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNombreCliente.setForeground(new java.awt.Color(0, 51, 255));
        lblNombreCliente.setText("lblNombreCliente");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Vehículo*");

        btnListaVehiculo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnListaVehiculo.setText("...");
        btnListaVehiculo.setEnabled(false);
        btnListaVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaVehiculoActionPerformed(evt);
            }
        });

        lblModelo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModelo.setForeground(new java.awt.Color(0, 51, 255));
        lblModelo.setText("lblModelo");

        txtVehiculoId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtVehiculoId.setEnabled(false);

        txtClienteId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtClienteId.setEnabled(false);

        txtMecanicoId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMecanicoId.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("RFC");

        lblRfc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRfc.setForeground(new java.awt.Color(0, 51, 255));
        lblRfc.setText("lblRfc");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Linea");

        lblLinea.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLinea.setForeground(new java.awt.Color(0, 51, 255));
        lblLinea.setText("lblLinea");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Año");

        lblAnio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAnio.setForeground(new java.awt.Color(0, 51, 255));
        lblAnio.setText("lblAnio");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtVehiculoId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(txtClienteId, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMecanicoId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnListaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblNombreMecanico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnListaCliente)
                        .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListaVehiculo)
                    .addComponent(txtVehiculoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListaEmpleado)
                    .addComponent(lblNombreMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMecanicoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tblDetalleServicios.setModel(new javax.swing.table.DefaultTableModel(
            null,
            encabezadoTabla
        ));
        jScrollPane1.setViewportView(tblDetalleServicios);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel19.setText("Total Servicios");

        lblTotalServicios.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTotalServicios.setForeground(new java.awt.Color(0, 51, 255));

        btnAgregarServicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAgregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/servicio.png"))); // NOI18N
        btnAgregarServicio.setText("Agregar Servicio");
        btnAgregarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServicioActionPerformed(evt);
            }
        });

        btnEliminarServicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEliminarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/trash.png"))); // NOI18N
        btnEliminarServicio.setText("Eliminar Servicio");
        btnEliminarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarServicioActionPerformed(evt);
            }
        });

        btnFinalizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/finish.png"))); // NOI18N
        btnFinalizar.setText("Finalizar Trabajo");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnVistaPreliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVistaPreliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/pdf.png"))); // NOI18N
        btnVistaPreliminar.setText("Vista Preliminar");
        btnVistaPreliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVistaPreliminarActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel22.setText("Total");

        lblTotalPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTotalPrecio.setForeground(new java.awt.Color(0, 51, 255));

        btnProcesar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnProcesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/process.png"))); // NOI18N
        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarServicio)
                        .addGap(13, 13, 13)
                        .addComponent(btnProcesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFinalizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTotalPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnVistaPreliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTotalServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel22)
                    .addComponent(lblTotalPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarServicio)
                    .addComponent(btnEliminarServicio)
                    .addComponent(btnFinalizar)
                    .addComponent(btnVistaPreliminar)
                    .addComponent(btnProcesar))
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel20.setText("Servicios y Detalles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoActionPerformed
        // TODO add your handling code here:

//        diagRegistroAutomovilesLista l = new diagRegistroAutomovilesLista(this, true);
//        l.btnSeleccionar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Id Auto Seleccionado: " + l.obtenerIdAutoSeleccionado());
//                if (l.obtenerIdAutoSeleccionado() == 0){
//                    JOptionPane.showMessageDialog(getParent(),
//                        "No se ha seleccionado ningun automovil de la lista",
//                        "Registro de Automóviles",
//                        JOptionPane.WARNING_MESSAGE);
//                } else {
//                    txtBuscar.setText(l.obtenerIdAutoSeleccionado()+"");
//                    buscarAutomovil();
//                    l.dispose();
//                }
//            }
//        });
//
//        l.setVisible(true);
    }//GEN-LAST:event_btnListadoActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
        buscarServicio();
    }//GEN-LAST:event_btnBuscar1ActionPerformed

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
            txtEstado.setText("NUEVO");
            btnEditar.setEnabled(false);
            btnGuardar.setEnabled(true);
            btnNuevo.setText("Cancelar");
            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/cancelar.png"))); // NOI18N
            ordenTrabajoActual = null;
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
        
        if(ordenTrabajoActual == null) {
            JOptionPane.showMessageDialog(getParent(), "No se puede actualizar una orden de servicio que no existe");
        } else{
            
            if(ordenTrabajoActual.getIdOrdenTrabajo() == 0) {
                JOptionPane.showMessageDialog(getParent(), "No se puede actualizar una orden de servicio que no existe");
            } else {

                    if(ordenTrabajoActual.getEstado().equals("N")){

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
                } else {
                    JOptionPane.showMessageDialog(getParent(),
                "No se puede actualizar una orden de servicio cuando ya esta en proceso o finalizada",
                "Orden de Servicio",
                JOptionPane.WARNING_MESSAGE);
                }
            }
            
        }
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if ( txtClienteId.getText().equals("") && txtVehiculoId.getText().equals("") && txtMecanicoId.getText().equals("") ){
            JOptionPane.showMessageDialog(getParent(),
                "Se deben llenar los datos marcados en asterisco como mínimo",
                "Orden de Servicio",
                JOptionPane.WARNING_MESSAGE);
        } else {
            OrdenTrabajo t = new OrdenTrabajo();
            OrdenTrabajoDao oDao = new OrdenTrabajoDao();
            if (ordenTrabajoActual != null){
                t = ordenTrabajoActual;
                t.setIdUsuario(1);
                t.setIdCliente(Integer.parseInt(txtClienteId.getText()));    
                t.setIdAuto(Integer.parseInt(txtVehiculoId.getText()));
                t.setIdEmpleadoAsignado(Integer.parseInt(txtMecanicoId.getText()));
                t.setEstado(String.valueOf(txtEstado.getText().charAt(0)));
                t.setFechaRegistro(txtFechaRegistro.getDate());
                
                if (oDao.actualizarOrdenDeTrabajo(t)){
                    ordenTrabajoActual = t;
                    deshabilitarCampos();
                    txtBuscar.setEnabled(true);                    
                    btnGuardar.setEnabled(false);
                    btnNuevo.setEnabled(true);
                    btnEditar.setEnabled(true);
                    btnNuevo.setText("Nuevo");
                    estadoRegistro = 0; // Sale de modo edición
//                    txtBuscar.setText(t.getIdOrdenTrabajo()+"");
                } else{
                    JOptionPane.showMessageDialog(getParent(),
                    "Ha ocurrido un error al registrar el cliente",
                    "Orden de Servicio",
                    JOptionPane.WARNING_MESSAGE);
                }
                
            } else {
                
                t = new OrdenTrabajo();
                t.setIdOrdenTrabajo(0);
                //hardcoded yet
                t.setIdUsuario(1);
                t.setIdCliente(Integer.parseInt(txtClienteId.getText()));    
                t.setIdAuto(Integer.parseInt(txtVehiculoId.getText()));
                t.setIdEmpleadoAsignado(Integer.parseInt(txtMecanicoId.getText()));
                t.setEstado(String.valueOf(txtEstado.getText().charAt(0)));
                t.setFechaRegistro(txtFechaRegistro.getDate());
                
                t = oDao.registrarCliente(t);
                
                if(t.getIdOrdenTrabajo() != 0){
                    ordenTrabajoActual = t;
                    deshabilitarCampos();
                    txtBuscar.setEnabled(true);                    
                    btnGuardar.setEnabled(false);
                    btnNuevo.setEnabled(true);
                    btnEditar.setEnabled(true);
                    btnNuevo.setText("Nuevo");
                    estadoRegistro = 0; // Sale de modo edición
                    txtBuscar.setText(t.getIdOrdenTrabajo()+"");
                }
            }
        }

//
//                a = aDao.registrarAutomovil(a);
//
//                if (a.getIdAuto() != 0){
//                    autoActual = a;
//                    deshabilitarCampos();
//                    txtBuscar.setEnabled(true);
//                    btnGuardar.setEnabled(false);
//                    btnNuevo.setEnabled(true);
//                    btnEditar.setEnabled(true);
//                    btnNuevo.setText("Nuevo");
//                    estadoRegistro = 0; // Sale de modo edición
//                }
//
//            }
//
//        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnListaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaClienteActionPerformed
        // TODO add your handling code here:

        diagRegistroClientesLista l = new diagRegistroClientesLista(this, true);
        l.btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Orden de Servicio Seleccionado: " + l.obtenerClienteSeleccionado());
                if (l.obtenerClienteSeleccionado() == 0){
                    JOptionPane.showMessageDialog(getParent(),
                        "No se ha seleccionado ningun cliente de la lista",
                        "Orden de Servicio",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    //txtBuscar.setText(l.obtenerEmpleadoSeleccionado()+"");
                    //buscarCliente();
                    Cliente c = new Cliente();
                    ClienteDao cDao = new ClienteDao();
                    txtClienteId.setText(l.obtenerClienteSeleccionado()+"");            
                    c = cDao.obtenerClienteById(l.obtenerClienteSeleccionado());
                    lblNombreCliente.setText(c.getNombreCompleto());
                    lblRfc.setText(c.getRfc());
                    l.dispose();
                }
            }
        });

        l.setVisible(true);
    }//GEN-LAST:event_btnListaClienteActionPerformed

    private void btnListaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaEmpleadoActionPerformed
        // TODO add your handling code here:
        
        diagRegistroEmpleadosLista l = new diagRegistroEmpleadosLista(this, true);
        l.btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Empleado Seleccionado: " + l.obtenerEmpleadoSeleccionado());
                if (l.obtenerEmpleadoSeleccionado() == 0){
                    JOptionPane.showMessageDialog(getParent(),
                        "No se ha seleccionado ningun empleado a asignar de la lista",
                        "Orden de Servicio",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    //txtBuscar.setText(l.obtenerEmpleadoSeleccionado()+"");
                    //buscarCliente();
                    Empleado emp = new Empleado();
                    EmpleadoDao eDao = new EmpleadoDao();
                    txtMecanicoId.setText(l.obtenerEmpleadoSeleccionado()+"");            
                    emp = eDao.obtenerEmpleadoById(l.obtenerEmpleadoSeleccionado());
                    lblNombreMecanico.setText(emp.getNombre() + " " + emp.getApePat() + " " + emp.getApeMat()); 
                    l.dispose();
                }
            }
        });

        l.setVisible(true);
    }//GEN-LAST:event_btnListaEmpleadoActionPerformed

    private void btnListaVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaVehiculoActionPerformed
        // TODO add your handling code here:
        diagRegistroAutomovilesLista l = new diagRegistroAutomovilesLista(this, true);  
        l.setIdCliente(Integer.parseInt(txtClienteId.getText()));
        l.btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                System.out.println("Id Auto Seleccionado: " + l.obtenerIdAutoSeleccionado());
                if (l.obtenerIdAutoSeleccionado() == 0){
                    JOptionPane.showMessageDialog(getParent(),
                                "No se ha seleccionado ningun automovil de la lista",
                                "Orden de Servicio",
                                JOptionPane.WARNING_MESSAGE);
                } else {
                    txtVehiculoId.setText(l.obtenerIdAutoSeleccionado()+"");
                    Automovil a = new Automovil();
                    AutomovilDao aDao = new AutomovilDao();
                    a = aDao.obtenerAutomovilById(l.obtenerIdAutoSeleccionado());
                    lblModelo.setText(a.getModelo());
                    lblLinea.setText(a.getLinea());
                    lblAnio.setText(a.getAnio()+"");
                    l.dispose();
                }
            }
        });
        
        l.setVisible(true);
    }//GEN-LAST:event_btnListaVehiculoActionPerformed

    private void btnAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioActionPerformed
        if(ordenTrabajoActual != null){
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
                        //Asignar servicio a la orden de trabajo
                        OrdenTrabajoDetalle d = new OrdenTrabajoDetalle();
                        OrdenTrabajoDetalleDao dDao = new OrdenTrabajoDetalleDao();
                        
                        int idODao = l.obtenerServicioSeleccionado();
                        
                        d.setIdOrdenTrabajo(ordenTrabajoActual.getIdOrdenTrabajo());
                        d.setIdCatalogoServicio(idODao);
                        
                        d = dDao.registrarOrdenTrabajoDetalle(d);
                        
                        if (d.getIdOrdenTrabajoDetalle() != 0){
                            JOptionPane.showMessageDialog(getParent(),
                                    "Se ha asignado el catalogo a la lista",
                                    "Orden de Servicio",
                                    JOptionPane.WARNING_MESSAGE); 
                            buscarServicio();
                        } else{
                            
                        }
                        
//                        a = aDao.obtenerServicioById(idServicio);
//                        a.setIdCatalogoServicio(ordenTrabajoActual.getIdOrdenTrabajo()); 
//
//                        aDao = new CatalogoServicioDao();
//                        if (aDao.actualizarAutomovil(a)){
//                        JOptionPane.showMessageDialog(getParent(),
//                                    "Se ha asignado el automóvil al cliente",
//                                    "Registro de Clientes",
//                                    JOptionPane.WARNING_MESSAGE);                        
//                        }
//                        buscarCliente();
                        l.dispose();
                    }
                }
            });

            l.setVisible(true);  
        } else {
            JOptionPane.showMessageDialog(getParent(),
                                    "Se necesita seleccionar una orden de trabajo para agregarle servicios",
                                    "Ordenes de Trabajo",
                                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarServicioActionPerformed

    private void btnEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServicioActionPerformed
        // TODO add your handling code here:
         if(ordenTrabajoActual != null ){

            if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea desasignar el servicio asignado", "Ordenes de Trabajo",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int fila = tblDetalleServicios.getSelectedRow();  
                int idOrdenDetalle = 0;
                try{
                    idOrdenDetalle = (int) model.getValueAt(fila, 0);
                    
                    OrdenTrabajoDetalle d = new OrdenTrabajoDetalle();
                    OrdenTrabajoDetalleDao dDao = new OrdenTrabajoDetalleDao();
                    
                    if (dDao.eliminarOrdenTrabajoDetalleById(idOrdenDetalle)){
                        
                    } else{
                        JOptionPane.showMessageDialog(getParent(),
                                    "Ocurrio un rror al eliminar el servicio",
                                    "Orden de Servicio",
                                    JOptionPane.WARNING_MESSAGE);   
                    }
                    
                    buscarServicio();

                }catch(ArrayIndexOutOfBoundsException ex){
                    System.out.println("Error: " + ex.getMessage());
                }
            } 
        } else{
        JOptionPane.showMessageDialog(getParent(),
                                "Se necesita seleccionar un servidio para desasignar",
                                "Orden de Servicio",
                                JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarServicioActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        // TODO add your handling code here:
        if(ordenTrabajoActual != null ){

            if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea finalizar la Orden", "Ordenes de Trabajo",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                  txtEstado.setText("FINALIZADO");
                  
                  OrdenTrabajo d = new OrdenTrabajo();
                  OrdenTrabajoDao dDao = new OrdenTrabajoDao();
                  
                  d = ordenTrabajoActual;                  
                  d.setEstado("F");
                  Calendar cal = Calendar.getInstance();
                  d.setFechaCompletado(new Date());
                  
                  if (dDao.actualizarOrdenDeTrabajo(d) ){
                  JOptionPane.showMessageDialog(getParent(),
                                "Orden Finalizada",
                                "Orden de Servicio",
                                JOptionPane.WARNING_MESSAGE);                     
                  }                  
                    
            } 
        } else{
        JOptionPane.showMessageDialog(getParent(),
                                "Nose puede finalizar una orden que no existe",
                                "Orden de Servicio",
                                JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnVistaPreliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVistaPreliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVistaPreliminarActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        // TODO add your handling code here:
        if(ordenTrabajoActual != null ){

            if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea procesar la Orden, una vez realizada esta acción no se podrá modificar", "Ordenes de Trabajo",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                  txtEstado.setText("PROCESO");
                  
                  OrdenTrabajo d = new OrdenTrabajo();
                  OrdenTrabajoDao dDao = new OrdenTrabajoDao();
                  
                  d = ordenTrabajoActual;                  
                  d.setEstado("P");
                  
                  if (dDao.actualizarOrdenDeTrabajo(d) ){
                  JOptionPane.showMessageDialog(getParent(),
                                "Orden en Proceso",
                                "Orden de Servicio",
                                JOptionPane.WARNING_MESSAGE);                     
                  }                  
                    
            } 
        } else{
        JOptionPane.showMessageDialog(getParent(),
                                "Nose puede procesar una orden que no existe",
                                "Orden de Servicio",
                                JOptionPane.WARNING_MESSAGE);
    }
        
    }//GEN-LAST:event_btnProcesarActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(frmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmOrdenTrabajo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListaCliente;
    private javax.swing.JButton btnListaEmpleado;
    private javax.swing.JButton btnListaVehiculo;
    private javax.swing.JButton btnListado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JButton btnVistaPreliminar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNombreMecanico;
    private javax.swing.JLabel lblRfc;
    private javax.swing.JLabel lblTotalPrecio;
    private javax.swing.JLabel lblTotalServicios;
    private javax.swing.JTable tblDetalleServicios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtClienteId;
    private javax.swing.JTextField txtEstado;
    private com.toedter.calendar.JDateChooser txtFechaRegistro;
    private com.toedter.calendar.JDateChooser txtFechaTerminado;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtMecanicoId;
    private javax.swing.JTextField txtNoOrdenTrabajo;
    private javax.swing.JTextField txtVehiculoId;
    // End of variables declaration//GEN-END:variables
}
