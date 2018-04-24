/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.vista;

import com.taller.dao.AutomovilDao;
import com.taller.dao.ClienteDao;
import com.taller.modelo.Automovil;
import com.taller.modelo.Cliente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class frmGestionClientes extends javax.swing.JFrame {

    /**
     * Creates new form frmGestionClientes
     */
    public frmGestionClientes() {
        listadoAutos = new ArrayList<Automovil>();
        model = new DefaultTableModel(encabezadoTabla, 0);
        initComponents();
        tblAutomoviles.getColumnModel().getColumn(0).setWidth(0);
        tblAutomoviles.getColumnModel().getColumn(0).setMinWidth(0);
        tblAutomoviles.getColumnModel().getColumn(0).setMaxWidth(0);
        getContentPane().setBackground(Color.WHITE);
    }
            
    private int estadoRegistro = 0;   
    private Cliente clienteActual = null;
    private ArrayList<Automovil> listadoAutos = null;
    private String [] encabezadoTabla = {"Id","Modelo","Marca","Linea","Año","Color","Placas"};
    private DefaultTableModel model;
    
    private void limpiarCampos() {
        txtRfc.setText("");
        chActivo.setSelected(false);
        txtNombre.setText("");
        txtCompania.setText("");
        txtCalle.setText("");
        txtCodigoPostal.setText("");
        txtCorreo.setText("");
        txtEstado.setText("");
        txtNoExt.setText("");
        txtNoInt.setText("");
        txtTelefono.setText("");
        txtEstado1.setText("");
        txtFax.setText("");
        model = new DefaultTableModel(encabezadoTabla, 0);
        tblAutomoviles.setModel(model);
    }
    
    private void habilitarCampos(){
        txtNombre.setEnabled(true);
        txtRfc.setEnabled(true);
        chActivo.setEnabled(true);
        txtCompania.setEnabled(true);
        txtCalle.setEnabled(true);
        txtCodigoPostal.setEnabled(true);
        txtCorreo.setEnabled(true);
        txtEstado.setEnabled(true);
        txtNoExt.setEnabled(true);
        txtNoInt.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtEstado1.setEnabled(true);
        txtFax.setEnabled(true);
    }
    
    private void deshabilitarCampos(){
        txtRfc.setEnabled(false);
        chActivo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtCompania.setEnabled(false);
        txtCalle.setEnabled(false);
        txtCodigoPostal.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtEstado.setEnabled(false);
        txtNoExt.setEnabled(false);
        txtNoInt.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEstado1.setEnabled(false);
        txtFax.setEnabled(false);
    }  
    
        private void buscarCliente(){
          Cliente c = new Cliente();
          ClienteDao cDao = new ClienteDao();
          clienteActual = null;
          model = new DefaultTableModel(encabezadoTabla, 0);
          
          if (txtBuscar.getText().equals(null) || txtBuscar.getText().equals("")){ 
            limpiarCampos();
            JOptionPane.showMessageDialog(rootPane, "Se necesita escribir un id");
          } else{
              try{
                   c = cDao.obtenerClienteById(Integer.parseInt(txtBuscar.getText()));
              }catch(NumberFormatException ex){
                  c.setIdCliente(0);
                  JOptionPane.showMessageDialog(rootPane, "Texto a buscar debe ser entero, detalles de error " + ex.getMessage());
              }
            if(c != null){
                txtRfc.setText(c.getRfc());
                txtCompania.setText(c.getCompania());
                if(c.getActivo().equals("1")){
                    chActivo.setSelected(true);
                } else {
                    chActivo.setSelected(false);
                }
                txtNombre.setText(c.getNombreCompleto());
                txtCorreo.setText(c.getEmail());
                txtCalle.setText(c.getCalle());
                txtNoExt.setText(c.getNumeroExterior());
                txtNoInt.setText(c.getNumeroInterior());
                txtCodigoPostal.setText(c.getCodigoPostal()+"");
                txtEstado.setText(c.getEstado());
                txtTelefono.setText(c.getTelefono());
                txtEstado1.setText(c.getCiudad());
                txtFax.setText(c.getFax());                
                clienteActual = c;
                
                // Cargar automoviles del cliente              
                AutomovilDao auDao = new AutomovilDao();
                listadoAutos = auDao.obtenerAutomovovilesFromClienteId(c.getIdCliente());
               
                for(Automovil a: listadoAutos){
                    Vector v = new Vector();
                    v.add(a.getIdAuto());
                    v.add(a.getModelo());
                    v.add(a.getMarca());
                    v.add(a.getLinea());
                    v.add(a.getAnio());
                    v.add(a.getColor());
                    v.add(a.getPlacas());
                    model.addRow(v);
                }   
                
                tblAutomoviles.setModel(model);
                tblAutomoviles.getColumnModel().getColumn(0).setWidth(0);
                tblAutomoviles.getColumnModel().getColumn(0).setMinWidth(0);
                tblAutomoviles.getColumnModel().getColumn(0).setMaxWidth(0);
                
                JOptionPane.showMessageDialog(rootPane, "Datos de cliente cargado correctamente ");
            } else{
                JOptionPane.showMessageDialog(rootPane, "Cliente no encontrado");                
                limpiarCampos();             
            }
          }
//        if (a != null){
//           txtPlacas.setText(a.getPlacas());
//           txtModelo.setText(a.getModelo()+"");
//           txtMarca.setText(a.getMarca());           
//           txtColor.setText(a.getColor());
//           btnEditar.setEnabled(true);
//           autoActual = a;
//        }     
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
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCompania = new javax.swing.JTextField();
        chActivo = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        txtRfc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnListado = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        txtNoExt = new javax.swing.JTextField();
        txtCodigoPostal = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNoInt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEstado1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAutomoviles = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnAsignarAuto = new javax.swing.JButton();
        btnEliminarAsignacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Clientes");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nombre*");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Compañia*");

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 51, 255));
        txtNombre.setEnabled(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtCompania.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCompania.setForeground(new java.awt.Color(0, 51, 255));
        txtCompania.setEnabled(false);

        chActivo.setBackground(new java.awt.Color(255, 255, 255));
        chActivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chActivo.setText("Activo");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("RFC*");

        txtRfc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtRfc.setForeground(new java.awt.Color(0, 51, 255));
        txtRfc.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                        .addComponent(chActivo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtCompania)))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCompania, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chActivo)
                    .addComponent(jLabel4)
                    .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

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
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(btnBuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Calle");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("No. Ext");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Código Postal");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Estado");

        txtCalle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCalle.setForeground(new java.awt.Color(0, 51, 255));
        txtCalle.setEnabled(false);

        txtNoExt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNoExt.setForeground(new java.awt.Color(0, 51, 255));
        txtNoExt.setEnabled(false);

        txtCodigoPostal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCodigoPostal.setForeground(new java.awt.Color(0, 51, 255));
        txtCodigoPostal.setEnabled(false);

        txtEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtEstado.setForeground(new java.awt.Color(0, 51, 255));
        txtEstado.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("No. Int");

        txtNoInt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNoInt.setForeground(new java.awt.Color(0, 51, 255));
        txtNoInt.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Telefono");

        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 51, 255));
        txtTelefono.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Correo");

        txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 51, 255));
        txtCorreo.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Fax");

        txtFax.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtFax.setForeground(new java.awt.Color(0, 51, 255));
        txtFax.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Ciudad");

        txtEstado1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtEstado1.setForeground(new java.awt.Color(0, 51, 255));
        txtEstado1.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(30, 30, 30)
                        .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtNoExt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtNoInt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtCorreo)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtFax))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNoExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtNoInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblAutomoviles.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        tblAutomoviles.setModel(new javax.swing.table.DefaultTableModel(
            null,
            encabezadoTabla
        ));
        tblAutomoviles.setModel(model);
        tblAutomoviles.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblAutomoviles);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Datos Básicos (Oblitatorios)");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Automóviles Asignados");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Datos para Facturacion");

        btnAsignarAuto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAsignarAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/auto.png"))); // NOI18N
        btnAsignarAuto.setText("Asignar");
        btnAsignarAuto.setMaximumSize(new java.awt.Dimension(129, 49));
        btnAsignarAuto.setMinimumSize(new java.awt.Dimension(129, 49));
        btnAsignarAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarAutoActionPerformed(evt);
            }
        });

        btnEliminarAsignacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEliminarAsignacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/trash.png"))); // NOI18N
        btnEliminarAsignacion.setText("Desasignar");
        btnEliminarAsignacion.setMaximumSize(new java.awt.Dimension(129, 49));
        btnEliminarAsignacion.setMinimumSize(new java.awt.Dimension(129, 49));
        btnEliminarAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAsignacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAsignarAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEliminarAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel10))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsignarAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoActionPerformed
        // TODO add your handling code here:

        diagRegistroClientesLista l = new diagRegistroClientesLista(this, true);
        l.btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cliente Seleccionado: " + l.obtenerClienteSeleccionado());
                if (l.obtenerClienteSeleccionado() == 0){
                    JOptionPane.showMessageDialog(getParent(),
                        "No se ha seleccionado ningun automovil de la lista",
                        "Registro de Clientes",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    txtBuscar.setText(l.obtenerClienteSeleccionado()+"");
                    buscarCliente();
                    l.dispose();
                }
            }
        });

        l.setVisible(true);
    }//GEN-LAST:event_btnListadoActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:

        buscarCliente();
        //        Automovil a = new Automovil();
        //        AutomovilDao aDao = new AutomovilDao();
        //        a = aDao.obtenerAutomovilById(Integer.parseInt(txtBuscar.getText()));
        //
        //        if (a != null){
            //           txtPlacas.setText(a.getPlacas());
            //           txtModelo.setText(a.getModelo()+"");
            //           txtMarca.setText(a.getMarca());
            //           txtColor.setText(a.getColor());
            //           btnEditar.setEnabled(true);
            //           autoActual = a;
            //        } else {
            //            JOptionPane.showMessageDialog(rootPane, "Automovil no encontrado");
            //            autoActual = null;
            //            limpiarCampos();
            //        }
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
            btnEditar.setEnabled(false);
            btnGuardar.setEnabled(true);
            btnNuevo.setText("Cancelar");
            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/taller/img/cancelar.png"))); // NOI18N
            clienteActual = null;
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

        if(clienteActual == null ) {
            JOptionPane.showMessageDialog(getParent(), "No se puede actualizar un auto que no existe");
        } else {

            if(clienteActual.getIdCliente() == 0){
                JOptionPane.showMessageDialog(getParent(), "No se puede actualizar un auto que no existe");
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
        if ( txtCompania.getText().equals("") && txtNombre.getText().equals("") && txtRfc.getText().equals("")){
            JOptionPane.showMessageDialog(getParent(),
                "Se deben llenar los datos marcados en asterisco como mínimo",
                "Registro de Clientes",
                JOptionPane.WARNING_MESSAGE);
        } else {
            Cliente c = new Cliente();
            if (clienteActual != null){
                ClienteDao aDao = new ClienteDao(); 
                c = clienteActual;
                c.setNombreCompleto(txtNombre.getText());
                c.setCompania(txtCompania.getText());
                c.setRfc(txtRfc.getText());
                if(chActivo.isSelected()){
                    c.setActivo("1");
                } else {
                    c.setActivo("0");
                }
                c.setCalle(txtCalle.getText());
                c.setNumeroExterior(txtNoExt.getText());
                c.setNumeroInterior(txtNoInt.getText());
                try{
                    c.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText()));
                }catch(NumberFormatException ex){
                    c.setCodigoPostal(0);
                }
                c.setEstado(txtEstado.getText());
                c.setTelefono(txtTelefono.getText());
                c.setEmail(txtCorreo.getText());
                c.setFax(txtFax.getText());
                c.setCiudad(txtEstado1.getText());
                        
                if ( aDao.actualizarCliente(c)) {
                    clienteActual = c;
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
                c.setIdCliente(0);
                ClienteDao aDao = new ClienteDao(); 
                c.setNombreCompleto(txtNombre.getText());
                c.setCompania(txtCompania.getText());
                c.setRfc(txtRfc.getText());
                if(chActivo.isSelected()){
                    c.setActivo("1");
                } else {
                    c.setActivo("0");
                }
                c.setCalle(txtCalle.getText());
                c.setNumeroExterior(txtNoExt.getText());
                c.setNumeroInterior(txtNoInt.getText());
                try{
                    c.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText()));
                }catch(NumberFormatException ex){
                    c.setCodigoPostal(0);
                }
                c.setEstado(txtEstado.getText());
                c.setTelefono(txtTelefono.getText());
                c.setEmail(txtCorreo.getText());   
                c.setFax(txtFax.getText());
                c.setCiudad(txtEstado1.getText());
                c = aDao.registrarCliente(c);
                
                if (  c.getIdCliente() != 0){
                    clienteActual = c;
                    deshabilitarCampos();
                    txtBuscar.setText(c.getIdCliente()+"");
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

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnAsignarAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarAutoActionPerformed
        
        if(clienteActual != null ){

            diagSeleccionAutomovilesAsignacion l = new diagSeleccionAutomovilesAsignacion(this, true);                 
            l.btnSeleccionar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                
                    System.out.println("Id Auto Seleccionado: " + l.obtenerIdAutoSeleccionado());
                    if (l.obtenerIdAutoSeleccionado() == 0){
                        JOptionPane.showMessageDialog(getParent(),
                                    "No se ha seleccionado ningun automovil de la lista",
                                    "Registro de Clientes",
                                    JOptionPane.WARNING_MESSAGE);
                    } else {
                        //Asignar automovil a Cliente y actualizar en BD
                        int idAuto = l.obtenerIdAutoSeleccionado();
                        Automovil a = new Automovil();
                        AutomovilDao aDao = new AutomovilDao();

                        a = aDao.obtenerAutomovilById(idAuto);
                        a.setIdCliente(clienteActual.getIdCliente()); 

                        aDao = new AutomovilDao();
                        if (aDao.actualizarAutomovil(a)){
                        JOptionPane.showMessageDialog(getParent(),
                                    "Se ha asignado el automóvil al cliente",
                                    "Registro de Clientes",
                                    JOptionPane.WARNING_MESSAGE);                        
                        }
                        buscarCliente();
                        l.dispose();
                    }
                }
            });

            l.setVisible(true);  

        } else{
            JOptionPane.showMessageDialog(getParent(),
                                    "Se necesita seleccionar un cliente para asignar un auto",
                                    "Registro de Clientes",
                                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAsignarAutoActionPerformed

    private void btnEliminarAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAsignacionActionPerformed
        // Preguntar si se desea eliminar la asignacion, de caso contrario no eliminar        
        
        if(clienteActual != null ){

            if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea desasignar el vehículo del cliente", "Registro de Automóviles",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int fila = tblAutomoviles.getSelectedRow();  
                int idAuto = 0;
                try{
                    idAuto = (int) model.getValueAt(fila, 0);
                    Automovil a = new Automovil();                
                    AutomovilDao aDao = new AutomovilDao();

                    a = aDao.obtenerAutomovilById(idAuto);
                    a.setIdCliente(-1); //Asignar el cliente -1 es desasignar del cliente actual

                    aDao = new AutomovilDao();
                        if (aDao.actualizarAutomovil(a)){
                        JOptionPane.showMessageDialog(getParent(),
                                    "Se ha asignado el automóvil al cliente",
                                    "Registro de Automóviles",
                                    JOptionPane.WARNING_MESSAGE);                        
                        }
                    buscarCliente();

                }catch(ArrayIndexOutOfBoundsException ex){
                    System.out.println("Error: " + ex.getMessage());
                }
            } 
        } else{
        JOptionPane.showMessageDialog(getParent(),
                                "Se necesita seleccionar un cliente para asignar un auto",
                                "Registro de Clientes",
                                JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarAsignacionActionPerformed

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
            java.util.logging.Logger.getLogger(frmGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGestionClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarAuto;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminarAsignacion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblAutomoviles;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCodigoPostal;
    private javax.swing.JTextField txtCompania;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtEstado1;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtNoExt;
    private javax.swing.JTextField txtNoInt;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
