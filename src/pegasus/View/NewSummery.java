/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.View;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import pegasus.Controller.AccountController;
import pegasus.Controller.CenterController;
import pegasus.Controller.ChitInformationController;
import pegasus.Controller.RemainingPacketController;
import pegasus.Controller.SheetDetailsController;
import pegasus.Controller.SummeryController;
import pegasus.Controller.Summery_lineController;
import pegasus.Model.Account;
import pegasus.Model.Center;
import pegasus.Model.Sheet_Details;
import pegasus.Model.Summery;
import pegasus.Model.Summery_line;

/**
 *
 * @author Chamil123
 */
public class NewSummery extends javax.swing.JDialog {

    private boolean dot = false;
    double commsion;
    double noCommsion;
    double lessCommsion;

    /**
     * Creates new form NewSummery
     */
    public NewSummery(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        jTable10.setVisible(false);
        jScrollPane6.setVisible(false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        jTextField2.setText(dateFormat.format(date));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

    }

    public void setLcNc() {
        double nocomAmount;
        if (jTextField6.getText().equals("")) {
            nocomAmount = 0;
        } else {
            nocomAmount = Double.parseDouble(jTextField6.getText());
        }

        double lesscomAmount;
        if (jTextField4.getText().equals("")) {
            lesscomAmount = 0;
        } else {
            lesscomAmount = Double.parseDouble(jTextField4.getText());
        }

        double nc = (nocomAmount * noCommsion) / 100;
        double lc = (lesscomAmount * lessCommsion) / 100;
        double nclc = nc + lc;
        jTextField15.setText("" + nclc);
    }

    public void loadTotalValues() {

        DecimalFormat df = new DecimalFormat("##.00");
        double fullCollection;
        if (jTextField12.getText().equals("")) {
            fullCollection = 0;
        } else {
            fullCollection = Double.parseDouble(jTextField12.getText());
        }
        double lcAndNc;
        if (jTextField15.getText().equals("")) {
            lcAndNc = 0;
        } else {
            lcAndNc = Double.parseDouble(jTextField15.getText());
        }
        double paperCash;
        if (jTextField16.getText().equals("")) {
            paperCash = 0;
        } else {
            paperCash = Double.parseDouble(jTextField16.getText());
        }
        double loan;
        if (jTextField18.getText().equals("")) {
            loan = 0;
        } else {
            loan = Double.parseDouble(jTextField18.getText());
        }
        double pastDue;
        if (jTextField20.getText().equals("")) {
            pastDue = 0;
        } else {
            pastDue = Double.parseDouble(jTextField20.getText());
        }
        double subTotalDue = fullCollection + lcAndNc + paperCash + loan + pastDue;
        jTextField22.setText("" + subTotalDue);

        double PaymentTot;
        double compay = (fullCollection * commsion) / 100;
        jTextField14.setText("" + compay);

        if (jTextField13.getText().equals("")) {
            PaymentTot = 0;
        } else {
            PaymentTot = Double.parseDouble(jTextField13.getText());
        }
        double commisionPay;
        if (jTextField14.getText().equals("")) {
            commisionPay = 0;
        } else {
            commisionPay = Double.parseDouble(jTextField14.getText());
        }
        double expenses;
        if (jTextField17.getText().equals("")) {
            expenses = 0;
        } else {
            expenses = Double.parseDouble(jTextField17.getText());
        }
        double excess;
        if (jTextField19.getText().equals("")) {
            excess = 0;
        } else {
            excess = Double.parseDouble(jTextField19.getText());
        }
        double cash;
        if (jTextField21.getText().equals("")) {
            cash = 0;
        } else {
            cash = Double.parseDouble(jTextField21.getText());
        }
        double subTotalPay = PaymentTot + commisionPay + expenses + excess + cash;
        jTextField23.setText("" + subTotalPay);

        if (subTotalDue > subTotalPay) {
            jTextField24.setText("" + subTotalPay);
            jTextField26.setText("" + df.format(subTotalDue - subTotalPay).toString());
            jTextField25.setText("");
            jTextField27.setText("");
        } else if (subTotalPay > subTotalDue) {
            jTextField25.setText("" + subTotalDue);
            jTextField27.setText("" + df.format(subTotalPay - subTotalDue).toString());
            jTextField24.setText("");
            jTextField26.setText("");
        } else if (subTotalPay == subTotalDue) {
            jTextField27.setText("0.00");
            jTextField24.setText("");
            jTextField25.setText("" + subTotalDue);
            jTextField26.setText("");
        }

        if (jTextField5.getText().equals("")) {
            jTextField12.setText("0");
        }
        if (jTextField7.getText().equals("")) {
            jTextField21.setText("0");
        }
        if (jTextField28.getText().equals("")) {
            jTextField19.setText("0");
        }
        if (jTextField8.getText().equals("")) {
            jTextField20.setText("0");
        }

        jTextField1.setText("");
        jTextField10.setText("");
        jTextField11.setText("");

    }

    public void tablePaymentTotal() {
        DefaultTableModel tb1 = (DefaultTableModel) jTable1.getModel();
        int rowCount = tb1.getRowCount();
        double totalPayment = 0;
        for (int i = 0; i < rowCount; i++) {
            totalPayment += Double.parseDouble(tb1.getValueAt(i, 2).toString());
        }
        jTextField13.setText("" + totalPayment);
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
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New summery");
        setMinimumSize(new java.awt.Dimension(458, 630));

        jPanel1.setMinimumSize(new java.awt.Dimension(458, 630));
        jPanel1.setPreferredSize(new java.awt.Dimension(458, 630));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable10.setRowHeight(18);
        jTable10.getTableHeader().setReorderingAllowed(false);
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable10MousePressed(evt);
            }
        });
        jTable10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable10KeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(jTable10);
        if (jTable10.getColumnModel().getColumnCount() > 0) {
            jTable10.getColumnModel().getColumn(0).setMinWidth(10);
            jTable10.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable10.getColumnModel().getColumn(1).setMinWidth(30);
            jTable10.getColumnModel().getColumn(1).setMaxWidth(20);
            jTable10.getColumnModel().getColumn(2).setMinWidth(90);
            jTable10.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 260, 170));

        jLabel1.setText(" Center name :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 20));

        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField2.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField2.setPreferredSize(new java.awt.Dimension(2, 19));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 80, -1));

        jLabel2.setText("            Current Date :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 110, 20));

        jTextField3.setEditable(false);
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField3.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField3.setPreferredSize(new java.awt.Dimension(2, 19));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 170, -1));

        jLabel3.setText("    Center id :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, 20));

        jLabel4.setText("  Less commision :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 90, 20));

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
        jTextField4.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField4.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 80, -1));

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField5.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField5.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 130, -1));

        jLabel5.setText("    Full collection :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 90, 20));

        jLabel6.setText("     No commision :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 90, 20));

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
        jTextField6.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField6.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 80, -1));

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField7.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField7.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField7MouseClicked(evt);
            }
        });
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 130, -1));

        jLabel7.setText("    Cash :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 50, 20));

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField8.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField8.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
        });
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField8MouseEntered(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 80, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField1.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField1.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField10.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField10.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10MouseClicked(evt);
            }
        });
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField11.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField11.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11MouseClicked(evt);
            }
        });
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });

        jButton1.setText("add");
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jLabel9.setText("Chit number");

        jLabel10.setText("Investment");

        jLabel11.setText("Payment");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 141, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 430, 70));

        jTextField9.setBackground(new java.awt.Color(255, 204, 102));
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField9.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField9.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 170, 22));

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chit number", "Amount", "Payment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 430, 160));

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(0, 0, 204));
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField12.setText("0");
        jTextField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField12.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField12.setPreferredSize(new java.awt.Dimension(2, 19));

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(0, 0, 204));
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField13.setText("0");
        jTextField13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField13.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField13.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel8.setText("Full collection :");

        jLabel13.setText("Total payment  :");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField14.setText("0");
        jTextField14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField14.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField14.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel14.setText("Commision pay :");

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField15.setText("0");
        jTextField15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField15.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField15.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel15.setText("LC and NC     :");

        jLabel16.setText("Paper cash    :");

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField16.setText("0");
        jTextField16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField16.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField16.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel17.setText("Expeneses       :");

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField17.setText("0");
        jTextField17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField17.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField17.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel18.setText("Loan              :");

        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField18.setText("0");
        jTextField18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField18.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField18.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel19.setText("Salary              :");

        jTextField19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField19.setText("0");
        jTextField19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField19.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField19.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel20.setText("Past due       :");

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField20.setText("0");
        jTextField20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField20.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField20.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel21.setText("Cash                :");

        jTextField21.setEditable(false);
        jTextField21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField21.setForeground(new java.awt.Color(0, 0, 204));
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField21.setText("0");
        jTextField21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField21.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField21.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel22.setText("Sub total       :");

        jTextField22.setEditable(false);
        jTextField22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField22.setText("0");
        jTextField22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField22.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField22.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel23.setText("Sub total          :");

        jTextField23.setEditable(false);
        jTextField23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField23.setText("0");
        jTextField23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField23.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField23.setPreferredSize(new java.awt.Dimension(2, 19));

        jTextField24.setEditable(false);
        jTextField24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField24.setText("0");
        jTextField24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField24.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField24.setPreferredSize(new java.awt.Dimension(2, 19));

        jTextField25.setEditable(false);
        jTextField25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField25.setText("0");
        jTextField25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField25.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField25.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel26.setText("Due               :");

        jTextField26.setEditable(false);
        jTextField26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField26.setForeground(new java.awt.Color(0, 0, 204));
        jTextField26.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField26.setText("0");
        jTextField26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField26.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField26.setPreferredSize(new java.awt.Dimension(2, 19));

        jLabel27.setText("Pay                  :");

        jTextField27.setEditable(false);
        jTextField27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField27.setForeground(new java.awt.Color(0, 0, 204));
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField27.setText("0");
        jTextField27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField27.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField27.setPreferredSize(new java.awt.Dimension(2, 19));

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel29.setText(" Items :");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 432, 250));

        jLabel12.setText("  PD :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 30, 20));

        jTextField28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField28.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField28.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField28MouseClicked(evt);
            }
        });
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });
        jTextField28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField28KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField28KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 60, -1));

        jLabel28.setText("Salary:");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 40, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MousePressed

    }//GEN-LAST:event_jTable10MousePressed

    private void jTable10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable10KeyPressed
        if (evt.getKeyCode() == 10) {
            try {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                String date = dateFormat.format(cal.getTime());

                int Row = jTable10.getSelectedRow();
                jTextField9.setText(jTable10.getValueAt(Row, 0).toString());
                String id = jTable10.getValueAt(Row, 1).toString();

                int searchExcisting = SummeryController.searchsummeryByDateAndId(jTextField2.getText(), id);
                if (searchExcisting == 0) {

                    String generalString = CenterController.getCenterTypeById(id);
                    if (generalString.equals("General")) {
                        jTextField5.grabFocus();
                        jTextField7.setText("0");
                    } else {

                        Sheet_Details sheetdetails = SheetDetailsController.searchByCenterId(id, jTextField2.getText());

                        if (sheetdetails != null) {
                            jTextField5.setText("" + sheetdetails.getSheetdetails_fullCollection());
                            jTextField7.setText("" + sheetdetails.getSheetdetails_cash());
                            jTextField28.setText("" + sheetdetails.getSheetdetails_excess());
                            jTextField8.setText("" + sheetdetails.getSheetdetails_pd());
                            jTextField12.setText("" + sheetdetails.getSheetdetails_fullCollection());
                            jTextField21.setText("" + sheetdetails.getSheetdetails_cash());
                            jTextField19.setText("" + sheetdetails.getSheetdetails_excess());
                            jTextField20.setText("" + sheetdetails.getSheetdetails_pd());
                            jTextField1.grabFocus();
                        } else {

                            double previousDue = SummeryController.searchPrivousDue(date, id);
                            jTextField8.setText("" + previousDue);
                            jTextField20.setText("" + previousDue);
                            jTextField5.grabFocus();
                        }
                    }

                    DefaultTableModel dtm = (DefaultTableModel) jTable10.getModel();
                    Center center = CenterController.searchCenterById(id);

                    jTextField3.setText("" + center.getCenter_id());
                    jTextField16.setText("" + center.getCenter_paperCash());
                    jTextField18.setText("" + center.getCenter_loan());
                    jTextField17.setText("" + center.getCenter_exp());
                    commsion = center.getCenter_com();
                    noCommsion = center.getCenter_noCom();
                    lessCommsion = center.getCenter_lessCom();

                    jScrollPane6.setVisible(false);
                    jTable10.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "currently added");
                    jTextField9.setText("");
                    jTextField3.setText("");
                    jTable10.setVisible(false);
                    jScrollPane6.setVisible(false);
                    jTextField9.grabFocus();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTable10KeyPressed

    private void jTable10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable10KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased

        //  loadTotalValues();
        DefaultTableModel dtm = (DefaultTableModel) jTable10.getModel();
        try {
            if (jTextField9.getText().trim().length() != 0) {
                List<Center> centerList = CenterController.searchCenterByName(jTextField9.getText().trim());
                String account;

                dtm.setRowCount(0);
                if (centerList != null) {
                    for (int i = 0; i < centerList.size(); i++) {
                        account = AccountController.searchAccountById("" + centerList.get(i).getAccount_id());
                        if (account != null) {
                            Vector v = new Vector();
                            v.add(centerList.get(i).getCenter_name());
                            v.add(centerList.get(i).getCenter_id());
                            v.add(account);
                            dtm.addRow(v);
                            jScrollPane6.setVisible(true);
                            jTable10.setVisible(true);
                        }
                    }

                    if (dtm.getRowCount() == 0) {
                        jScrollPane6.setVisible(false);
                        jTable10.setVisible(false);
                    } else {
                        jScrollPane6.setVisible(true);
                        jTable10.setVisible(true);
                    }
                }
                centerList = null;
            } else {
                dtm.setRowCount(0);
                jScrollPane6.setVisible(false);
                jTable10.setVisible(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        if (evt.getKeyCode() == 40) {
            if (jTextField9.getText().length() != 0) {
                jTable10.grabFocus();
                ListSelectionModel selectionModel = jTable10.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
            } else {
                jScrollPane6.setVisible(false);
                jTable10.setVisible(false);
            }

        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        jTextField5.grabFocus();
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        if (!jTextField5.getText().equals("")) {
            try {
                String generalString = CenterController.getCenterTypeById(jTextField3.getText());
                if (generalString.equals("General")) {
                    jTextField1.grabFocus();
                    jTextField7.setText("0");
                } else {
                    jTextField7.grabFocus();
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            jTextField5.grabFocus();
        }

    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        if (!jTextField7.getText().equals("")) {
            jTextField28.grabFocus();

        } else {
            jTextField7.grabFocus();
        }
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        jTextField1.grabFocus();
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        if (!jTextField1.getText().equals("")) {
            jTextField10.grabFocus();
//            try {
//                Double.parseDouble(jTextField1.getText());
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(null, "Please insert into numerics");
//                jTextField1.grabFocus();
//                jTextField1.setText("");
//            }
        } else {
            jTextField1.grabFocus();
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        if (!jTextField10.getText().equals("")) {
            jTextField11.grabFocus();
            try {
                Double.parseDouble(jTextField10.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please insert into numerics");
                jTextField10.grabFocus();
                jTextField10.setText("");
            }
        } else {
            jTextField10.grabFocus();
        }
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        if (!jTextField11.getText().equals("")) {
            jButton1.grabFocus();
            try {
                Double.parseDouble(jTextField11.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please insert into numerics");
                jTextField11.grabFocus();
                jTextField11.setText("");
            }
        } else {
            jTextField11.grabFocus();
        }
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased

        jTextField12.setText(jTextField5.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        jTextField21.setText(jTextField7.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        jTextField20.setText(jTextField8.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed

        DefaultTableModel tb1 = (DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();
        v.add(jTextField1.getText());
        v.add(Double.parseDouble(jTextField10.getText()));
        v.add(Double.parseDouble(jTextField11.getText()));
        tb1.addRow(v);

        jTable1.scrollRectToVisible(jTable1.getCellRect(jTable1.getRowCount() - 1, 0, true));
        tablePaymentTotal();
        loadTotalValues();

        jLabel30.setText("" + tb1.getRowCount());

        jTextField1.grabFocus();

    }//GEN-LAST:event_jButton1KeyPressed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        setLcNc();
        loadTotalValues();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        setLcNc();
        loadTotalValues();
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField28KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField28KeyReleased
        jTextField19.setText(jTextField28.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField28KeyReleased

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed

        jTextField1.grabFocus();
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed

        jTextField6.grabFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed

        jTextField1.grabFocus();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        int i = jTable1.getSelectedRow();
        String f = "" + i;
        if (f != null) {
            int g = JOptionPane.showConfirmDialog(null, "Are you sure want to remove Item");
            if (g == JOptionPane.YES_OPTION) {
                dtm.removeRow(i);
                tablePaymentTotal();
                loadTotalValues();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        loadTotalValues();

        try {

            int searchExcisting = SummeryController.searchsummeryByDateAndId(jTextField2.getText(), jTextField3.getText());
            if (searchExcisting == 0) {

                double fullCollection = 0;
                if (jTextField5.getText().length() != 0) {
                    fullCollection = Double.parseDouble(jTextField5.getText());
                } else {
                    fullCollection = 0;
                }

                double cash = 0;
                if (jTextField7.getText().length() != 0) {
                    cash = Double.parseDouble(jTextField7.getText());
                } else {
                    cash = 0;
                }
                double passDue = 0;
                if (jTextField8.getText().length() != 0) {
                    passDue = Double.parseDouble(jTextField8.getText());
                } else {
                    passDue = 0;
                }

                double noCommision = 0;
                if (jTextField6.getText().length() != 0) {
                    noCommision = Double.parseDouble(jTextField6.getText());
                } else {
                    noCommision = 0;
                }
                double lessCommision = 0;
                if (jTextField4.getText().length() != 0) {
                    lessCommision = Double.parseDouble(jTextField4.getText());
                } else {
                    lessCommision = 0;
                }

                double dailypayment = 0;
                if (jTextField27.getText().length() != 0) {
                    dailypayment = Double.parseDouble(jTextField27.getText());
                } else {
                    dailypayment = 0;
                }
                double dailydue = 0;
                if (jTextField26.getText().length() != 0) {
                    dailydue = Double.parseDouble(jTextField26.getText());
                } else {
                    dailydue = 0;
                }

                Summery summery = new Summery(null, jTextField2.getText(),
                        jTextField9.getText(), fullCollection, cash, passDue,
                        noCommision,
                        lessCommision,
                        Double.parseDouble(jTextField15.getText()),
                        Double.parseDouble(jTextField16.getText()),
                        Double.parseDouble(jTextField14.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        Double.parseDouble(jTextField13.getText()),
                        Double.parseDouble(jTextField18.getText()),
                        Double.parseDouble(jTextField19.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        dailypayment, dailydue,
                        Integer.parseInt(jTextField3.getText()));

                try {
                    int isAdded = SummeryController.addSummery(summery);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }

                int maxId = 0;
                try {

                    int rowCount = dtm.getRowCount();
                    String chitNumber;
                    double investment;
                    double payment;
                    Summery_line summeryLine;
                    int isAdded;
                    for (int i = 0; i < rowCount; i++) {
                        chitNumber = dtm.getValueAt(i, 0).toString();
                        investment = Double.parseDouble(dtm.getValueAt(i, 1).toString());
                        payment = Double.parseDouble(dtm.getValueAt(i, 2).toString());

                        summeryLine = new Summery_line(null, chitNumber, investment, payment, jTextField2.getText(), Integer.parseInt(jTextField3.getText()));
                        isAdded = Summery_lineController.addSummery_line(summeryLine);
                    }
                    summeryLine = null;

                    int sumerylineListList = Summery_lineController.searchTotalChitBydate(jTextField2.getText());
                    ChitInformationController.setStaticTotalChits(sumerylineListList);
                    int remainingPackets = CenterController.searchTotalCenters(jTextField2.getText());
                    ChitInformationController.setStaticRemainingPackets(remainingPackets);

                    int completedPacket = SummeryController.completedSummery(jTextField2.getText());
                    ChitInformationController.completedPackets(completedPacket);

//                    boolean isDeleteRemainingPacket = RemainingPacketController.deletePacketById(jTextField3.getText());
//                    if (isDeleteRemainingPacket == true) {
//                        ChitInformationController.deleteTemperNotification(1);
//                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }
                DecimalFormat df = new DecimalFormat("##0.00");
                try {

                    String CenterType = CenterController.getCenterTypeById(jTextField3.getText());
                    if (CenterType.equals("Normal")) {

                        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
                        JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                        String reportSource = "C:\\pegasusReports/normalSummery.jrxml";
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("Date", jTextField2.getText());
                        params.put("centerName", jTextField9.getText());
                        params.put("centerNo", jTextField3.getText());

                        if (jTextField4.getText().length() != 0) {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "L/C :");
                        } else {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "");
                        }
                        if (jTextField6.getText().length() != 0) {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "N/C :");
                        } else {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "");
                        }

                        double FC = Double.parseDouble(jTextField12.getText());
                        params.put("FC", df.format(FC));
                        double NC = Double.parseDouble(jTextField15.getText());
                        params.put("NC", df.format(NC));
                        double PC = Double.parseDouble(jTextField16.getText());
                        params.put("PC", df.format(PC));
                        double LN = Double.parseDouble(jTextField18.getText());
                        params.put("LN", df.format(LN));
                        double PD = Double.parseDouble(jTextField20.getText());
                        params.put("PD", df.format(PD));

                        double TPY = Double.parseDouble(jTextField13.getText());
                        params.put("TPY", df.format(TPY));
                        double COM = Double.parseDouble(jTextField14.getText());
                        params.put("COM", df.format(COM));
                        double EXP = Double.parseDouble(jTextField17.getText());
                        params.put("EXP", df.format(EXP));
                        double EXC = Double.parseDouble(jTextField19.getText());
                        params.put("EXC", df.format(EXC));
                        double CAH = Double.parseDouble(jTextField21.getText());
                        params.put("CAH", df.format(CAH));

                        double SubDue = Double.parseDouble(jTextField22.getText());
                        params.put("SubDue", df.format(SubDue));
                        if (jTextField24.getText().length() != 0) {
                            double SubPayLeft = Double.parseDouble(jTextField24.getText());
                            params.put("SubPayLeft", df.format(SubPayLeft));
                        } else {
                            params.put("SubPayLeft", "");
                        }

                        double SubPay = Double.parseDouble(jTextField23.getText());
                        params.put("SubPay", df.format(SubPay));

                        if (jTextField25.getText().length() != 0) {
                            double SubDueRight = Double.parseDouble(jTextField25.getText());
                            params.put("SubDueRight", df.format(SubDueRight));
                        } else {
                            params.put("SubDueRight", "");
                        }

                        if (jTextField27.getText().length() != 0) {
                            double Pay = Double.parseDouble(jTextField27.getText());
                            params.put("Pay", df.format(Pay));
                            params.put("dueLine", "");
                            params.put("PayLine", "_________________");
                            params.put("PayString", "pay :");
                            params.put("dueString", "");

                        } else {
                            params.put("Pay", "");
                        }

                        if (jTextField26.getText().length() != 0) {
                            double Due = Double.parseDouble(jTextField26.getText());
                            params.put("Due", df.format(Due));
                            params.put("PayLine", "");
                            params.put("dueLine", "_________________");
                            params.put("dueString", ": due");
                            params.put("PayString", "");
                        } else {

                            params.put("Due", "");
                        }

                        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

                      //  JasperManager.printReport(jasperPrint, false);
                        JasperPrintManager.printReport(jasperPrint, false);
                        
                        if (jTextField9.getText().equals("JOTHI")) {
                            DateFormat dfd = new SimpleDateFormat("dd-MM-yyyy");
                            Date dateobj = new Date();
                            String pdfName = dfd.format(dateobj);
                            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\pegasusReports/" + pdfName + ".pdf");

                            SendMail sm = new SendMail();
                            sm.send2();
                        }

                        dtm.setRowCount(0);
                        jTextField5.setText("");
                        jTextField7.setText("");
                        jTextField28.setText("");
                        jTextField8.setText("");
                        jTextField4.setText("");
                        jTextField6.setText("");
                        jTextField12.setText("0");
                        jTextField15.setText("0");
                        jTextField16.setText("0");
                        jTextField18.setText("0");
                        jTextField20.setText("0");
                        jTextField22.setText("0");
                        jTextField24.setText("0");
                        jTextField26.setText("0");

                        jTextField13.setText("0");
                        jTextField14.setText("0");
                        jTextField17.setText("0");
                        jTextField19.setText("0");
                        jTextField21.setText("0");
                        jTextField23.setText("0");
                        jTextField25.setText("0");
                        jTextField27.setText("0");

                        jTextField9.setText("");
                        jTextField3.setText("");

                        jTextField9.grabFocus();
                    } else if (CenterType.equals("General")) {
                        String account = AccountController.searchAccountBynameByCenterId(jTextField3.getText());

                        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
                        JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                        String reportSource = "C:\\pegasusReports/normalGeneral.jrxml";
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("Date", jTextField2.getText());
                        params.put("centerName", jTextField9.getText());
                        params.put("centerNo", jTextField3.getText());

                        if (jTextField4.getText().length() != 0) {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "L/C :");
                        } else {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "");
                        }
                        if (jTextField6.getText().length() != 0) {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "N/C :");
                        } else {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "");
                        }
                        params.put("accountName", account);

                        double FC = Double.parseDouble(jTextField12.getText());
                        params.put("FC", df.format(FC));

                        double TPY = Double.parseDouble(jTextField13.getText());
                        params.put("TPY", df.format(TPY));

                        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//                        JasperManager.printReport(jasperPrint, false);
                        
                        JasperPrintManager.printReport(jasperPrint, false);
                        if (jTextField9.getText().equals("JSP")) {
                            DateFormat dfd = new SimpleDateFormat("dd-MM-yyyy");
                            Date dateobj = new Date();
                            String pdfName = dfd.format(dateobj);
                            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\pegasusReports/" + pdfName + ".pdf");

                            SendMail sm = new SendMail();
                            sm.send2();
                        }

                        dtm.setRowCount(0);
                        jTextField5.setText("");
                        jTextField7.setText("");
                        jTextField28.setText("");
                        jTextField8.setText("");
                        jTextField4.setText("");
                        jTextField6.setText("");
                        jTextField12.setText("0");
                        jTextField15.setText("0");
                        jTextField16.setText("0");
                        jTextField18.setText("0");
                        jTextField20.setText("0");
                        jTextField22.setText("0");
                        jTextField24.setText("0");
                        jTextField26.setText("0");

                        jTextField13.setText("0");
                        jTextField14.setText("0");
                        jTextField17.setText("0");
                        jTextField19.setText("0");
                        jTextField21.setText("0");
                        jTextField23.setText("0");
                        jTextField25.setText("0");
                        jTextField27.setText("0");

                        jTextField9.setText("");
                        jTextField3.setText("");

                        jTextField9.grabFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "error");
                    }
                } catch (JRException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "asasda asd asd as");
            }
            Runtime.getRuntime().runFinalization();
            Runtime.getRuntime().gc();
            System.gc();

//            SendMail sm = new SendMail();
//            sm.send2();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained

    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost

    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField4.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        setLcNc();
        loadTotalValues();
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField6.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        setLcNc();
        loadTotalValues();
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField8.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        jTextField20.setText(jTextField8.getText());
        loadTotalValues();

    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField28KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField28KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField28.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        jTextField21.setText(jTextField7.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField28KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField7.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        jTextField21.setText(jTextField7.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField5.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        jTextField12.setText(jTextField5.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField11.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        char vChar = evt.getKeyChar();
        if (jTextField10.getText().equals("")) {
            dot = false;
        }
        if (dot == false) {
            if (vChar == '.') {
                dot = true;
            } else if (!(Character.isDigit(vChar)
                    || (vChar == KeyEvent.VK_BACK_SPACE)
                    || (vChar == KeyEvent.VK_DELETE))) {
                evt.consume();
            }
        } else if (!(Character.isDigit(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int isAddedLine = 0;
        int isAdded = 0;
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        loadTotalValues();

        try {

            int searchExcisting = SummeryController.searchsummeryByDateAndId(jTextField2.getText(), jTextField3.getText());

            if (searchExcisting == 0) {

                double fullCollection = 0;
                if (jTextField5.getText().length() != 0) {
                    fullCollection = Double.parseDouble(jTextField5.getText());
                } else {
                    fullCollection = 0;
                }

                double cash = 0;
                if (jTextField7.getText().length() != 0) {
                    cash = Double.parseDouble(jTextField7.getText());
                } else {
                    cash = 0;
                }
                double passDue = 0;
                if (jTextField8.getText().length() != 0) {
                    passDue = Double.parseDouble(jTextField8.getText());
                } else {
                    passDue = 0;
                }

                double noCommision = 0;
                if (jTextField6.getText().length() != 0) {
                    noCommision = Double.parseDouble(jTextField6.getText());
                } else {
                    noCommision = 0;
                }
                double lessCommision = 0;
                if (jTextField4.getText().length() != 0) {
                    lessCommision = Double.parseDouble(jTextField4.getText());
                } else {
                    lessCommision = 0;
                }

                double dailypayment = 0;
                if (jTextField27.getText().length() != 0) {
                    dailypayment = Double.parseDouble(jTextField27.getText());
                } else {
                    dailypayment = 0;
                }
                double dailydue = 0;
                if (jTextField26.getText().length() != 0) {
                    dailydue = Double.parseDouble(jTextField26.getText());
                } else {
                    dailydue = 0;
                }
                Summery summery = new Summery(null, jTextField2.getText(),
                        jTextField9.getText(), fullCollection, cash, passDue,
                        noCommision,
                        lessCommision,
                        Double.parseDouble(jTextField15.getText()),
                        Double.parseDouble(jTextField16.getText()),
                        Double.parseDouble(jTextField14.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        Double.parseDouble(jTextField13.getText()),
                        Double.parseDouble(jTextField18.getText()),
                        Double.parseDouble(jTextField19.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        dailypayment, dailydue,
                        Integer.parseInt(jTextField3.getText()));

                try {
                    isAdded = SummeryController.addSummery(summery);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }

                int maxId = 0;
                try {
                    Summery_line summeryLine;
                    int rowCount = dtm.getRowCount();
                    String chitNumber;
                    double investment;
                    double payment;
                    for (int i = 0; i < rowCount; i++) {
                        chitNumber = dtm.getValueAt(i, 0).toString();
                        investment = Double.parseDouble(dtm.getValueAt(i, 1).toString());
                        payment = Double.parseDouble(dtm.getValueAt(i, 2).toString());

                        summeryLine = new Summery_line(null, chitNumber, investment, payment, jTextField2.getText(), Integer.parseInt(jTextField3.getText()));
                        isAddedLine = Summery_lineController.addSummery_line(summeryLine);

                    }
                    if (isAddedLine == 1 && isAdded == 1) {
                        JOptionPane.showMessageDialog(null, "Successfully saved");
                    }
                    dtm.setRowCount(0);
                    jTextField5.setText("");
                    jTextField7.setText("");
                    jTextField28.setText("");
                    jTextField8.setText("");
                    jTextField4.setText("");
                    jTextField6.setText("");
                    jTextField12.setText("0");
                    jTextField15.setText("0");
                    jTextField16.setText("0");
                    jTextField18.setText("0");
                    jTextField20.setText("0");
                    jTextField22.setText("0");
                    jTextField24.setText("0");
                    jTextField26.setText("0");

                    jTextField13.setText("0");
                    jTextField14.setText("0");
                    jTextField17.setText("0");
                    jTextField19.setText("0");
                    jTextField21.setText("0");
                    jTextField23.setText("0");
                    jTextField25.setText("0");
                    jTextField27.setText("0");

                    jTextField9.setText("");
                    jTextField3.setText("");

                    jTextField9.grabFocus();
                    summeryLine = null;

                    int sumerylineListList = Summery_lineController.searchTotalChitBydate(jTextField2.getText());
                    ChitInformationController.setStaticTotalChits(sumerylineListList);
                    int remainingPackets = CenterController.searchTotalCenters(jTextField2.getText());
                    ChitInformationController.setStaticRemainingPackets(remainingPackets);
                    int completedPacket = SummeryController.completedSummery(jTextField2.getText());
                    ChitInformationController.completedPackets(completedPacket);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }
                DecimalFormat df = new DecimalFormat("##0.00");

            } else {
                JOptionPane.showMessageDialog(null, "asasda asd asd as");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        jTextField8.selectAll();
    }//GEN-LAST:event_jTextField8FocusGained

    private void jTextField8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseEntered
        jTextField8.selectAll();
    }//GEN-LAST:event_jTextField8MouseEntered

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        jTextField4.selectAll();
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        jTextField6.selectAll();
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == 80) {
            jButton2.grabFocus();

        }

    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        loadTotalValues();

        try {

            int searchExcisting = SummeryController.searchsummeryByDateAndId(jTextField2.getText(), jTextField3.getText());
            if (searchExcisting == 0) {

                double fullCollection = 0;
                if (jTextField5.getText().length() != 0) {
                    fullCollection = Double.parseDouble(jTextField5.getText());
                } else {
                    fullCollection = 0;
                }

                double cash = 0;
                if (jTextField7.getText().length() != 0) {
                    cash = Double.parseDouble(jTextField7.getText());
                } else {
                    cash = 0;
                }
                double passDue = 0;
                if (jTextField8.getText().length() != 0) {
                    passDue = Double.parseDouble(jTextField8.getText());
                } else {
                    passDue = 0;
                }

                double noCommision = 0;
                if (jTextField6.getText().length() != 0) {
                    noCommision = Double.parseDouble(jTextField6.getText());
                } else {
                    noCommision = 0;
                }
                double lessCommision = 0;
                if (jTextField4.getText().length() != 0) {
                    lessCommision = Double.parseDouble(jTextField4.getText());
                } else {
                    lessCommision = 0;
                }

                double dailypayment = 0;
                if (jTextField27.getText().length() != 0) {
                    dailypayment = Double.parseDouble(jTextField27.getText());
                } else {
                    dailypayment = 0;
                }
                double dailydue = 0;
                if (jTextField26.getText().length() != 0) {
                    dailydue = Double.parseDouble(jTextField26.getText());
                } else {
                    dailydue = 0;
                }

                Summery summery = new Summery(null, jTextField2.getText(),
                        jTextField9.getText(), fullCollection, cash, passDue,
                        noCommision,
                        lessCommision,
                        Double.parseDouble(jTextField15.getText()),
                        Double.parseDouble(jTextField16.getText()),
                        Double.parseDouble(jTextField14.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        Double.parseDouble(jTextField13.getText()),
                        Double.parseDouble(jTextField18.getText()),
                        Double.parseDouble(jTextField19.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        dailypayment, dailydue,
                        Integer.parseInt(jTextField3.getText()));

                try {
                    int isAdded = SummeryController.addSummery(summery);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }

                int maxId = 0;
                try {

                    int rowCount = dtm.getRowCount();
                    String chitNumber;
                    double investment;
                    double payment;
                    Summery_line summeryLine;
                    int isAdded;
                    for (int i = 0; i < rowCount; i++) {
                        chitNumber = dtm.getValueAt(i, 0).toString();
                        investment = Double.parseDouble(dtm.getValueAt(i, 1).toString());
                        payment = Double.parseDouble(dtm.getValueAt(i, 2).toString());

                        summeryLine = new Summery_line(null, chitNumber, investment, payment, jTextField2.getText(), Integer.parseInt(jTextField3.getText()));
                        isAdded = Summery_lineController.addSummery_line(summeryLine);
                    }
                    summeryLine = null;

                    int sumerylineListList = Summery_lineController.searchTotalChitBydate(jTextField2.getText());
                    ChitInformationController.setStaticTotalChits(sumerylineListList);
                    int remainingPackets = CenterController.searchTotalCenters(jTextField2.getText());
                    ChitInformationController.setStaticRemainingPackets(remainingPackets);

                    int completedPacket = SummeryController.completedSummery(jTextField2.getText());
                    ChitInformationController.completedPackets(completedPacket);

//                    boolean isDeleteRemainingPacket = RemainingPacketController.deletePacketById(jTextField3.getText());
//                    if (isDeleteRemainingPacket == true) {
//                        ChitInformationController.deleteTemperNotification(1);
//                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }
                DecimalFormat df = new DecimalFormat("##0.00");
                try {

                    String CenterType = CenterController.getCenterTypeById(jTextField3.getText());
                    if (CenterType.equals("Normal")) {

                        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
                        JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                        String reportSource = "C:\\pegasusReports/normalSummery.jrxml";
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("Date", jTextField2.getText());
                        params.put("centerName", jTextField9.getText());
                        params.put("centerNo", jTextField3.getText());

                        if (jTextField4.getText().length() != 0) {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "L/C :");
                        } else {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "");
                        }
                        if (jTextField6.getText().length() != 0) {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "N/C :");
                        } else {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "");
                        }

                        double FC = Double.parseDouble(jTextField12.getText());
                        params.put("FC", df.format(FC));
                        double NC = Double.parseDouble(jTextField15.getText());
                        params.put("NC", df.format(NC));
                        double PC = Double.parseDouble(jTextField16.getText());
                        params.put("PC", df.format(PC));
                        double LN = Double.parseDouble(jTextField18.getText());
                        params.put("LN", df.format(LN));
                        double PD = Double.parseDouble(jTextField20.getText());
                        params.put("PD", df.format(PD));

                        double TPY = Double.parseDouble(jTextField13.getText());
                        params.put("TPY", df.format(TPY));
                        double COM = Double.parseDouble(jTextField14.getText());
                        params.put("COM", df.format(COM));
                        double EXP = Double.parseDouble(jTextField17.getText());
                        params.put("EXP", df.format(EXP));
                        double EXC = Double.parseDouble(jTextField19.getText());
                        params.put("EXC", df.format(EXC));
                        double CAH = Double.parseDouble(jTextField21.getText());
                        params.put("CAH", df.format(CAH));

                        double SubDue = Double.parseDouble(jTextField22.getText());
                        params.put("SubDue", df.format(SubDue));
                        if (jTextField24.getText().length() != 0) {
                            double SubPayLeft = Double.parseDouble(jTextField24.getText());
                            params.put("SubPayLeft", df.format(SubPayLeft));
                        } else {
                            params.put("SubPayLeft", "");
                        }

                        double SubPay = Double.parseDouble(jTextField23.getText());
                        params.put("SubPay", df.format(SubPay));

                        if (jTextField25.getText().length() != 0) {
                            double SubDueRight = Double.parseDouble(jTextField25.getText());
                            params.put("SubDueRight", df.format(SubDueRight));
                        } else {
                            params.put("SubDueRight", "");
                        }

                        if (jTextField27.getText().length() != 0) {
                            double Pay = Double.parseDouble(jTextField27.getText());
                            params.put("Pay", df.format(Pay));
                            params.put("dueLine", "");
                            params.put("PayLine", "_________________");
                            params.put("PayString", "pay :");
                            params.put("dueString", "");

                        } else {
                            params.put("Pay", "");
                        }

                        if (jTextField26.getText().length() != 0) {
                            double Due = Double.parseDouble(jTextField26.getText());
                            params.put("Due", df.format(Due));
                            params.put("PayLine", "");
                            params.put("dueLine", "_________________");
                            params.put("dueString", ": due");
                            params.put("PayString", "");
                        } else {

                            params.put("Due", "");
                        }

                        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//                        JasperManager.printReport(jasperPrint, false);
                        JasperPrintManager.printReport(jasperPrint, false);

                        if (jTextField9.getText().equals("JSP")) {
                            DateFormat dfd = new SimpleDateFormat("dd-MM-yyyy");
                            Date dateobj = new Date();
                            String pdfName = dfd.format(dateobj);
                            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\pegasusReports/" + pdfName + ".pdf");

                            SendMail sm = new SendMail();
                            sm.send2();
                        }

                        dtm.setRowCount(0);
                        jTextField5.setText("");
                        jTextField7.setText("");
                        jTextField28.setText("");
                        jTextField8.setText("");
                        jTextField4.setText("");
                        jTextField6.setText("");
                        jTextField12.setText("0");
                        jTextField15.setText("0");
                        jTextField16.setText("0");
                        jTextField18.setText("0");
                        jTextField20.setText("0");
                        jTextField22.setText("0");
                        jTextField24.setText("0");
                        jTextField26.setText("0");

                        jTextField13.setText("0");
                        jTextField14.setText("0");
                        jTextField17.setText("0");
                        jTextField19.setText("0");
                        jTextField21.setText("0");
                        jTextField23.setText("0");
                        jTextField25.setText("0");
                        jTextField27.setText("0");

                        jTextField9.setText("");
                        jTextField3.setText("");

                        jTextField9.grabFocus();
                    } else if (CenterType.equals("General")) {
                        String account = AccountController.searchAccountBynameByCenterId(jTextField3.getText());

                        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
                        JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                        String reportSource = "C:\\pegasusReports/normalGeneral.jrxml";
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("Date", jTextField2.getText());
                        params.put("centerName", jTextField9.getText());
                        params.put("centerNo", jTextField3.getText());

                        if (jTextField4.getText().length() != 0) {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "L/C :");
                        } else {
                            params.put("lesscomAmount", jTextField4.getText());
                            params.put("lcString", "");
                        }
                        if (jTextField6.getText().length() != 0) {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "N/C :");
                        } else {
                            params.put("nocomAmount", jTextField6.getText());
                            params.put("ncString", "");
                        }

                        params.put("accountName", account);

                        double FC = Double.parseDouble(jTextField12.getText());
                        params.put("FC", df.format(FC));

                        double TPY = Double.parseDouble(jTextField13.getText());
                        params.put("TPY", df.format(TPY));
                        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//                        JasperManager.printReport(jasperPrint, false);
                        
                        JasperPrintManager.printReport(jasperPrint, false);
                        if (jTextField9.getText().equals("JSP")) {
                            DateFormat dfd = new SimpleDateFormat("dd-MM-yyyy");
                            Date dateobj = new Date();
                            String pdfName = dfd.format(dateobj);
                            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\pegasusReports/" + pdfName + ".pdf");

                            SendMail sm = new SendMail();
                            sm.send2();
                        }

                        dtm.setRowCount(0);
                        jTextField5.setText("");
                        jTextField7.setText("");
                        jTextField28.setText("");
                        jTextField8.setText("");
                        jTextField4.setText("");
                        jTextField6.setText("");
                        jTextField12.setText("0");
                        jTextField15.setText("0");
                        jTextField16.setText("0");
                        jTextField18.setText("0");
                        jTextField20.setText("0");
                        jTextField22.setText("0");
                        jTextField24.setText("0");
                        jTextField26.setText("0");

                        jTextField13.setText("0");
                        jTextField14.setText("0");
                        jTextField17.setText("0");
                        jTextField19.setText("0");
                        jTextField21.setText("0");
                        jTextField23.setText("0");
                        jTextField25.setText("0");
                        jTextField27.setText("0");

                        jTextField9.setText("");
                        jTextField3.setText("");

                        jTextField9.grabFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "error");
                    }
                } catch (JRException ex) {
                    Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "asasda asd asd as");
            }
            Runtime.getRuntime().runFinalization();
            Runtime.getRuntime().gc();
            System.gc();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewSummery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        jTextField5.selectAll();
    }//GEN-LAST:event_jTextField5MouseClicked

    private void jTextField7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseClicked
        jTextField7.selectAll();
    }//GEN-LAST:event_jTextField7MouseClicked

    private void jTextField28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField28MouseClicked
        jTextField28.selectAll();
    }//GEN-LAST:event_jTextField28MouseClicked

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        jTextField1.selectAll();
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseClicked
        jTextField10.selectAll();
    }//GEN-LAST:event_jTextField10MouseClicked

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        jTextField11.selectAll();
    }//GEN-LAST:event_jTextField11MouseClicked

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
            java.util.logging.Logger.getLogger(NewSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewSummery dialog = new NewSummery(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
