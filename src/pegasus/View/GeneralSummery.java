/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.View;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import org.exolab.castor.dsml.Exporter;
import pegasus.Controller.AccountController;
import pegasus.Controller.GeneralSummeryLineController;
import pegasus.Controller.General_summeryController;
import pegasus.Controller.SummeryController;
import pegasus.Controller.Summery_lineController;
import pegasus.Model.Account;
import pegasus.Model.GeneralSummeryLine;
import pegasus.Model.General_Summery;
import pegasus.Model.Summery;
import pegasus.Model.Summery_line;

/**
 *
 * @author Chamil123
 */
public class GeneralSummery extends javax.swing.JDialog {

    private boolean dot = false;
    //ServerConnector serverConnector;
    double commsion;
    double noCommsion;
    double lessCommsion;

    /**
     * Creates new form NewSummery
     */
    public GeneralSummery(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        loadAccounts();

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

        if (jTextField7.getText().equals("")) {
            jTextField21.setText("0");
        }
        if (jTextField28.getText().equals("")) {
            jTextField19.setText("0");
        }
        if (jTextField8.getText().equals("")) {
            jTextField20.setText("0");
        }

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
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
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
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("General summery");
        setMinimumSize(new java.awt.Dimension(458, 630));
        setUndecorated(true);

        jPanel1.setMinimumSize(new java.awt.Dimension(458, 630));
        jPanel1.setPreferredSize(new java.awt.Dimension(458, 630));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(" Center name :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 20));

        jTextField2.setBackground(new java.awt.Color(255, 204, 102));
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

        jLabel6.setText("     No commision :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 90, 20));

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
        jTextField6.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField6.setPreferredSize(new java.awt.Dimension(2, 19));
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
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 170, -1));

        jLabel7.setText("    Cash :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 50, 20));

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField8.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField8.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8MouseClicked(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 80, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 430, 230));

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

        jLabel19.setText("Excess             :");

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
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
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
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 432, 250));

        jLabel12.setText("PD :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 40, 20));

        jTextField28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField28.setMinimumSize(new java.awt.Dimension(2, 19));
        jTextField28.setPreferredSize(new java.awt.Dimension(2, 19));
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });
        jTextField28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField28KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField28KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField28KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 170, -1));

        jLabel28.setText("EXC:");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 30, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 170, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        if (!jTextField7.getText().equals("")) {
            jTextField28.grabFocus();

        } else {
            jTextField7.grabFocus();
        }
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
//        jTextField1.grabFocus();
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        jTextField21.setText(jTextField7.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        jTextField20.setText(jTextField8.getText());
        loadTotalValues();
    }//GEN-LAST:event_jTextField8KeyReleased

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
        jTextField8.grabFocus();
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jTextField6.grabFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
//        jTextField1.grabFocus();
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

            int searchExcisting = General_summeryController.searchsummeryByDateAndId(jTextField2.getText(), jTextField3.getText());
            if (searchExcisting == 0) {

                double totalFc = 0;
                if (jTextField12.getText().length() != 0) {
                    totalFc = Double.parseDouble(jTextField12.getText());
                } else {
                    totalFc = 0;
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

                double paymentPay = 0;
                if (jTextField27.getText().length() != 0) {
                    paymentPay = Double.parseDouble(jTextField27.getText());
                } else {
                    paymentPay = 0;
                }
                General_Summery generalSummery = new General_Summery(null,
                        jTextField2.getText(),
                        jComboBox1.getSelectedItem().toString(), totalFc, cash, passDue,
                        noCommision,
                        lessCommision,
                        Double.parseDouble(jTextField15.getText()),
                        Double.parseDouble(jTextField16.getText()),
                        Double.parseDouble(jTextField14.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        Double.parseDouble(jTextField13.getText()),
                        Double.parseDouble(jTextField18.getText()),
                        Double.parseDouble(jTextField19.getText()),
                        Double.parseDouble("" + paymentPay),
                        Integer.parseInt(jTextField3.getText()));

                int isAddedgensum = General_summeryController.addGeneral(generalSummery);

                int maxId = 0;
                DecimalFormat df = new DecimalFormat("##0.00");
                maxId = General_summeryController.getMaxID();
                int rowCount = dtm.getRowCount();
                String summerynames;
                double fullCollection;
                double payment;
                GeneralSummeryLine generalsummeryLine;
                int isAdd;
                for (int i = 0; i < rowCount; i++) {
                    summerynames = dtm.getValueAt(i, 0).toString();
                    fullCollection = Double.parseDouble(dtm.getValueAt(i, 1).toString());
                    payment = Double.parseDouble(dtm.getValueAt(i, 2).toString());

                    generalsummeryLine = new GeneralSummeryLine(null, summerynames, fullCollection, payment, jTextField2.getText(), maxId);
                    isAdd = GeneralSummeryLineController.addGeneralSummery_line(generalsummeryLine);
                }
                generalsummeryLine = null;
//                try {

                DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                String reportSource = "C:\\pegasusReports/GeneralSummery.jrxml";
                Map<String, Object> params = new HashMap<String, Object>();
//                    params.put("Date", jTextField2.getText());
//                    params.put("generalName", jComboBox1.getSelectedItem().toString() + " General");
//                    params.put("accountNo", jTextField3.getText());
//
//                    if (jTextField4.getText().length() != 0) {
//                        params.put("lesscomAmount", jTextField4.getText());
//                        params.put("lcString", "L/C :");
//                    } else {
//                        params.put("lesscomAmount", jTextField4.getText());
//                        params.put("lcString", "");
//                    }
//                    if (jTextField6.getText().length() != 0) {
//                        params.put("nocomAmount", jTextField6.getText());
//                        params.put("ncString", "N/C :");
//                    } else {
//                        params.put("nocomAmount", jTextField6.getText());
//                        params.put("ncString", "");
//                    }
//
//                    double FC = Double.parseDouble(jTextField12.getText());
//                    params.put("FC", df.format(FC));
//                    double NC = Double.parseDouble(jTextField15.getText());
//                    params.put("NC", df.format(NC));
//                    double PC = Double.parseDouble(jTextField16.getText());
//                    params.put("PC", df.format(PC));
//                    double LN = Double.parseDouble(jTextField18.getText());
//                    params.put("LN", df.format(LN));
//                    double PD = Double.parseDouble(jTextField20.getText());
//                    params.put("PD", df.format(PD));
////////
//                    double TPY = Double.parseDouble(jTextField13.getText());
//                    params.put("TPY", df.format(TPY));
//                    double COM = Double.parseDouble(jTextField14.getText());
//                    params.put("COM", df.format(COM));
//                    double EXP = Double.parseDouble(jTextField17.getText());
//                    params.put("EXP", df.format(EXP));
//                    double EXC = Double.parseDouble(jTextField19.getText());
//                    params.put("EXC", df.format(EXC));
//                    double CAH = Double.parseDouble(jTextField21.getText());
//                    params.put("CAH", df.format(CAH));
////////
//                    double SubDue = Double.parseDouble(jTextField22.getText());
//                    params.put("SubDue", df.format(SubDue));
//                    if (jTextField24.getText().length() != 0) {
//                        double SubPayLeft = Double.parseDouble(jTextField24.getText());
//                        params.put("SubPayLeft", df.format(SubPayLeft));
//                    } else {
//                        params.put("SubPayLeft", "");
//                    }
//
//                    double SubPay = Double.parseDouble(jTextField23.getText());
//                    params.put("SubPay", df.format(SubPay));
//
//                    if (jTextField25.getText().length() != 0) {
//                        double SubDueRight = Double.parseDouble(jTextField25.getText());
//                        params.put("SubDueRight", df.format(SubDueRight));
//                    } else {
//                        params.put("SubDueRight", "");
//                    }
//
//                    if (jTextField27.getText().length() != 0) {
//                        double Pay = Double.parseDouble(jTextField27.getText());
//                        params.put("Pay", df.format(Pay));
//                        params.put("dueLine", "");
//                        params.put("PayLine", "_________________");
//                        params.put("PayString", "pay :");
//                        params.put("dueString", "");
//
//                    } else {
//                        params.put("Pay", "");
//                    }
//
//                    if (jTextField26.getText().length() != 0) {
//                        double Due = Double.parseDouble(jTextField26.getText());
//                        params.put("Due", df.format(Due));
//                        params.put("PayLine", "");
//                        params.put("dueLine", "_________________");
//                        params.put("dueString", "due :");
//                        params.put("PayString", "");
//                    } else {
//
//                        params.put("Due", "");
//                    }
//                    JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
//                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//                    JasperManager.printReport(jasperPrint, true);

                if (jComboBox1.getSelectedItem().equals("kochchikade")) {
                    try {
                        DateFormat dfd = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateobj = new Date();
                        String pdfName = dfd.format(dateobj);

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pegasus", "root", "1234");
                        String masterReportSource = "C:\\pegasusReports/report2.jrxml";
                        params.put("date", pdfName);
                        params.put("gen_number", "5");
                        JasperReport jasperReport = JasperCompileManager.compileReport(masterReportSource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
                        JasperManager.printReport(jasperPrint, false);


//                        Exporter exporter;
//                        exporter = new JRDocxExporter();
//                        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//                        File exportReportFile = new File("D:\\Temp\\report.docx");
//                        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
//                        exporter.exportReport();

                        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\pegasusReports/" + pdfName + "kochchikade.pdf");
//
                        System.out.println("sssssssss");

                        SendMail sm = new SendMail();
                        sm.send2();

                    } catch (JRException ex) {
                        Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                //  catch (JRException ex) {
//                    Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
//                }
            } else {
                JOptionPane.showMessageDialog(null, "asasda asd asd as");
            }
            Runtime.getRuntime().runFinalization();
            Runtime.getRuntime().gc();
            System.gc();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

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
        jTextField19.setText(jTextField28.getText());
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int isAddedLine = 0;
        int isAdded = 0;
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        loadTotalValues();

        try {

            int searchExcisting = SummeryController.searchsummeryByDateAndId(jTextField2.getText(), jTextField3.getText());

            if (searchExcisting == 0) {

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

                int maxId = 0;
                try {
                    String chitNumber;
                    double investment;
                    double payment;
                    int rowCount = dtm.getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        chitNumber = dtm.getValueAt(i, 0).toString();
                        investment = Double.parseDouble(dtm.getValueAt(i, 1).toString());
                        payment = Double.parseDouble(dtm.getValueAt(i, 2).toString());

                        Summery_line summeryLine = new Summery_line(null, chitNumber, investment, payment, jTextField2.getText(), Integer.parseInt(jTextField3.getText()));
                        isAddedLine = Summery_lineController.addSummery_line(summeryLine);

                    }
                    if (isAddedLine == 1 && isAdded == 1) {
                        JOptionPane.showMessageDialog(null, "Successfully saved");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
                }
                DecimalFormat df = new DecimalFormat("##0.00");

            } else {
                JOptionPane.showMessageDialog(null, "asasda asd asd as");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        try {
            String name = jComboBox1.getSelectedItem().toString();
            if (!name.equals("")) {
                Account account = AccountController.searcAccountByName(name);
                jTextField3.setText("" + account.getAccount_id());
                int searchExcisting = General_summeryController.searchsummeryByDateAndId(jTextField2.getText(), jTextField3.getText());
                if (searchExcisting == 0) {

                    jTextField16.setText("" + account.getAccount_paperCash());
                    jTextField18.setText("" + account.getAccount_loan());
                    jTextField17.setText("" + account.getAccount_exp());
                    int accountId = account.getAccount_id();
                    String date = jTextField2.getText();
                    commsion = account.getAccount_com();

                    List<Summery> summery = SummeryController.searchsummeryByDateAndAccoundID(date, accountId);

                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.setRowCount(0);
                    double totalFC = 0;
                    double totalPayment = 0;
                    double noComAmmount = 0;
                    double lessComAmmount = 0;
                    for (int i = 0; i < summery.size(); i++) {
                        noComAmmount += summery.get(i).getSummery_noComAmount();
                        lessComAmmount += summery.get(i).getSummery_lessComAmount();

                        Vector v = new Vector();
                        totalFC += summery.get(i).getSummery_fc();
                        totalPayment += summery.get(i).getSummery_slippaytot();
                        v.add(summery.get(i).getSummery_name());
                        v.add(summery.get(i).getSummery_fc());
                        v.add(summery.get(i).getSummery_slippaytot());
                        dtm.addRow(v);
                    }
                    jTextField12.setText("" + totalFC);
                    jTextField13.setText("" + totalPayment);

                    double compay = totalFC * commsion;
                    jTextField14.setText("" + compay);

                    jTextField4.setText("" + lessComAmmount);
                    jTextField6.setText("" + noComAmmount);

                    double lc = (lessComAmmount * account.getAccount_lessCom()) / 100;
                    double nc = (noComAmmount * account.getAccount_noCom()) / 100;

                    double lcandnc = lc + nc;
                    jTextField15.setText("" + lcandnc);

                    loadTotalValues();
                } else {
                    JOptionPane.showMessageDialog(null, name + " general is currently added");
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.setRowCount(0);
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
                    jComboBox1.setSelectedIndex(0);
                    jTextField3.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, name + " Plese select general name");
            }
            jLabel30.setText("" + jTable1.getRowCount());
        } catch (SQLException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseClicked
        jTextField8.selectAll();
    }//GEN-LAST:event_jTextField8MouseClicked

    private void jTextField28KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField28KeyPressed
        if (evt.getKeyCode() == 80) {
            jButton2.grabFocus();

        }
    }//GEN-LAST:event_jTextField28KeyPressed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
        if (evt.getKeyCode() == 80) {
            jButton2.grabFocus();

        }
    }//GEN-LAST:event_jTextField8KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        loadTotalValues();
        try {

            int searchExcisting = General_summeryController.searchsummeryByDateAndId(jTextField2.getText(), jTextField3.getText());
            if (searchExcisting == 0) {

                double totalFc = 0;
                if (jTextField12.getText().length() != 0) {
                    totalFc = Double.parseDouble(jTextField12.getText());
                } else {
                    totalFc = 0;
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

                double paymentPay = 0;
                if (jTextField27.getText().length() != 0) {
                    paymentPay = Double.parseDouble(jTextField27.getText());
                } else {
                    paymentPay = 0;
                }
                General_Summery generalSummery = new General_Summery(null,
                        jTextField2.getText(),
                        jComboBox1.getSelectedItem().toString(), totalFc, cash, passDue,
                        noCommision,
                        lessCommision,
                        Double.parseDouble(jTextField15.getText()),
                        Double.parseDouble(jTextField16.getText()),
                        Double.parseDouble(jTextField14.getText()),
                        Double.parseDouble(jTextField17.getText()),
                        Double.parseDouble(jTextField13.getText()),
                        Double.parseDouble(jTextField18.getText()),
                        Double.parseDouble(jTextField19.getText()),
                        Double.parseDouble("" + paymentPay),
                        Integer.parseInt(jTextField3.getText()));

                int isAddedgensum = General_summeryController.addGeneral(generalSummery);

                int maxId = 0;
                DecimalFormat df = new DecimalFormat("##0.00");
                maxId = General_summeryController.getMaxID();
                int rowCount = dtm.getRowCount();
                String summerynames;
                double fullCollection;
                double payment;
                GeneralSummeryLine generalsummeryLine;
                int isAdd;

                for (int i = 0; i < rowCount; i++) {
                    summerynames = dtm.getValueAt(i, 0).toString();
                    fullCollection = Double.parseDouble(dtm.getValueAt(i, 1).toString());
                    payment = Double.parseDouble(dtm.getValueAt(i, 2).toString());

                    generalsummeryLine = new GeneralSummeryLine(null, summerynames, fullCollection, payment, jTextField2.getText(), maxId);
                    isAdd = GeneralSummeryLineController.addGeneralSummery_line(generalsummeryLine);
                }
                generalsummeryLine = null;
                try {

                    DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
                    JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                    String reportSource = "C:\\pegasusReports/GeneralSummery.jrxml";
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("Date", jTextField2.getText());
                    params.put("generalName", jComboBox1.getSelectedItem().toString() + " General");
                    params.put("accountNo", jTextField3.getText());

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
//////
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
//////
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
                        params.put("dueString", "due :");
                        params.put("PayString", "");
                    } else {

                        params.put("Due", "");
                    }
                    JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//                    JasperManager.printReport(jasperPrint, true);

                    JasperPrintManager.printReport(jasperPrint, true);
                } catch (JRException ex) {
                    Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "asasda asd asd as");
            }
            Runtime.getRuntime().runFinalization();
            Runtime.getRuntime().gc();
            System.gc();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2KeyPressed

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
            java.util.logging.Logger.getLogger(GeneralSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralSummery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GeneralSummery dialog = new GeneralSummery(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
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
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

    private void loadAccounts() {
        try {
            List<Account> accounts = AccountController.getAllAccount();
            DefaultComboBoxModel cmb = (DefaultComboBoxModel) jComboBox1.getModel();
            for (int i = 0; i < accounts.size(); i++) {
                cmb.addElement(accounts.get(i).getAccount_name());
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralSummery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
