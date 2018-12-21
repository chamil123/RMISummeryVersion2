/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import pegasus.Controller.AccountController;
import pegasus.Controller.CenterController;
import pegasus.Controller.GeneralSummeryLineController;
import pegasus.Controller.General_summeryController;
import pegasus.Controller.SummeryController;
import pegasus.Controller.Summery_lineController;
import pegasus.Model.Center;
import pegasus.Model.General_Summery;
import pegasus.Model.GetFolderSize;
import pegasus.Model.Summery;

/**
 *
 * @author Chamil123
 */
public class settings extends javax.swing.JDialog {

    int year;
    int month;
    int date;
    int hour;
    int hours;
    int min;
    int sec;
    int labe0 = 1;
    int labe1 = 2;
    int labe2 = 3;
    int labe3 = 1;
    int labe4 = 2;
    int labe5 = 3;

    /**
     * Creates new form settings
     */
    public settings(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTable10.setVisible(false);
        jScrollPane6.setVisible(false);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        jDateChooser1.setDate(date);

        javax.swing.Timer t = new javax.swing.Timer(1000, new settings.ClockListener());
        t.start();
        setLocationRelativeTo(this);
        jProgressBar1.setVisible(false);
        jLabel30.setVisible(false);
        System.gc();

        jButton2.setVisible(true);

        try {

            GetFolderSize SizeF = new GetFolderSize();
            jLabel34.setText(SizeF.size("Akash"));
            jLabel32.setText(SizeF.m(1992));

            String Lname = "No backup found ...";
            File f0 = new File("C:/Sankalpa/summery.01");
            String list0[] = f0.list();
            for (String name : list0) {
                Lname = name;
            }

            File f1 = new File("C:/Sankalpa/summery.01/" + Lname + "");
            double bytes = f1.length();
            double kilobytes = (bytes / 1024);
            double megabytes0 = Math.round(kilobytes / 1024);
            double megabytes = (kilobytes / 1024);
            double gigabytes0 = Math.round(megabytes / 1024);
            double gigabytes = (megabytes / 1024);
            StringBuffer kb0 = null;
            if (gigabytes0 != 0.0) {
                kb0 = new StringBuffer(gigabytes + "");

            } else if (megabytes0 != 0.0) {
                kb0 = new StringBuffer(megabytes + "");

            } else {
                kb0 = new StringBuffer(kilobytes + "");

            }
            int index1 = kb0.indexOf(".");
            String kb1 = kb0.substring(0, index1);
            String kb2 = kb0.substring(index1 + 1);
            String kb3[] = kb2.split("");
            if (gigabytes0 != 0.0) {
                jLabel29.setText(kb1 + "." + kb3[1] + "" + kb3[2] + " GB");

            } else if (megabytes0 != 0.0) {
                jLabel29.setText(kb1 + "." + kb3[1] + "" + kb3[2] + " MB");

            } else {
                jLabel29.setText(kb1 + "." + kb3[1] + " KB");

            }

            String s0[] = Lname.split("_");
            StringBuffer sb1 = new StringBuffer(s0[1]);
            int index = sb1.indexOf("(");
            String P2 = sb1.substring(index + 1);
            StringBuffer sb2 = new StringBuffer(P2);
            int index2 = sb2.indexOf(")");
            String P3 = sb2.substring(0, index2);
            String s[] = P3.split("-");
            int H = Integer.parseInt(s[0]);
            int h = H;
            String ap = " PM";
            if (H > 50) {
                h = (H - 50) - 12;

            }
            if (H
                    < 50) {
                ap = " AM";

            }

            jLabel27.setText(s0[0] + "  @  " + h + "." + s[1] + "." + s[2] + ap);

        } catch (ArrayIndexOutOfBoundsException e) {
            jLabel27.setText("No backup found");
        } catch (NullPointerException e) {
            jLabel27.setText("No backup found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClockListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
            date = cal.get(Calendar.DATE);
            hour = cal.get(Calendar.HOUR_OF_DAY);
            min = cal.get(Calendar.MINUTE);
            sec = cal.get(Calendar.SECOND);

        }
    }

    public void m0(int i0) {
        labe0 = i0;

    }

    public void m1(int i1) {
        labe1 = i1;

    }

    public void m2(int i2) {
        labe2 = i2;

    }

    public void m3(int i3) {
        labe3 = i3;

    }

    public void m4(int i4) {
        labe4 = i4;

    }

    public void m5(int i5) {
        labe5 = i5;

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
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jTextField9 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");
        setMaximumSize(new java.awt.Dimension(873, 395));
        setMinimumSize(new java.awt.Dimension(873, 395));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Settings");

        jPanel2.setMinimumSize(new java.awt.Dimension(660, 490));
        jPanel2.setLayout(null);
        jPanel2.add(jTextField2);
        jTextField2.setBounds(210, 250, 260, 20);

        jButton5.setText("insert");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(370, 280, 100, 23);

        jButton10.setText("Backup Now...");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);
        jButton10.setBounds(370, 80, 102, 23);

        jLabel23.setText("This option will backup data on the Pegasuss...");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(130, 40, 290, 14);
        jPanel2.add(jProgressBar1);
        jProgressBar1.setBounds(40, 185, 430, 14);

        jLabel24.setForeground(new java.awt.Color(37, 38, 184));
        jLabel24.setText(" ");
        jPanel2.add(jLabel24);
        jLabel24.setBounds(580, 157, 35, 15);

        jLabel25.setText(" ");
        jPanel2.add(jLabel25);
        jLabel25.setBounds(40, 200, 290, 14);

        jLabel26.setText("Last backup  :");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(150, 70, 80, 14);

        jLabel31.setText("Total backup count  :");
        jPanel2.add(jLabel31);
        jLabel31.setBounds(116, 120, 120, 14);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 0, 0));
        jLabel32.setText(".");
        jPanel2.add(jLabel32);
        jLabel32.setBounds(230, 120, 140, 15);

        jLabel33.setText("Total backup(s) size  :");
        jPanel2.add(jLabel33);
        jLabel33.setBounds(112, 145, 130, 14);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 0, 0));
        jLabel34.setText(".");
        jPanel2.add(jLabel34);
        jLabel34.setBounds(230, 145, 120, 15);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 0, 0));
        jLabel27.setText("data");
        jPanel2.add(jLabel27);
        jLabel27.setBounds(230, 70, 150, 16);

        jLabel28.setText("Backup size  :");
        jPanel2.add(jLabel28);
        jLabel28.setBounds(152, 95, 80, 14);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 0, 0));
        jLabel29.setText("0.0 KB");
        jPanel2.add(jLabel29);
        jLabel29.setBounds(230, 95, 140, 16);

        jLabel30.setFont(new java.awt.Font("T11ThibusTru", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 0, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Backup is successful....");
        jPanel2.add(jLabel30);
        jLabel30.setBounds(40, 190, 430, 18);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(370, 180, 100, 23);

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(370, 110, 100, 23);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backup_2.png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(40, 60, 80, 80);

        jLabel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Backup ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel2.add(jLabel22);
        jLabel22.setBounds(10, 0, 480, 320);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DeletePacket"));
        jPanel3.setLayout(null);

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        jPanel3.add(jDateChooser1);
        jDateChooser1.setBounds(122, 27, 204, 20);

        jLabel2.setText("Select Date   :");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(28, 27, 76, 20);

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

        jPanel3.add(jScrollPane6);
        jScrollPane6.setBounds(122, 88, 200, 80);

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
        jPanel3.add(jTextField9);
        jTextField9.setBounds(122, 65, 204, 22);

        jTextField1.setEditable(false);
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(jTextField1);
        jTextField1.setBounds(122, 100, 204, 16);

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(121, 130, 63, 23);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete2.png"))); // NOI18N
        jPanel3.add(jLabel5);
        jLabel5.setBounds(20, 90, 90, 70);

        jLabel6.setText("Center name :");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(28, 65, 76, 20);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Correct Date"));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calndar.png"))); // NOI18N

        jLabel3.setText("Wrong date  :");

        jLabel7.setText("Corrent date :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addContainerGap())
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel8)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {

            System.gc();
            jButton2.setVisible(false);
            jButton3.setEnabled(false);
            jButton10.setEnabled(false);
            jLabel26.setVisible(false);
            jLabel27.setVisible(false);
            jLabel28.setVisible(false);
            jLabel29.setVisible(false);
            jLabel30.setVisible(false);
            jLabel31.setVisible(false);
            jLabel32.setVisible(false);
            jLabel33.setVisible(false);
            jLabel34.setVisible(false);
            jProgressBar1.setVisible(true);

            new Thread(new Runnable() {

                public void run() {
                    System.gc();

                    for (int i = 0; i < 101; i++) {

                        if (i == 18) {
                            m3(18);
                        }

                        if (i == 83) {
                            m4(83);
                        }

                        if (i == 100) {
                            m5(100);
                        }

                        try {
                            jProgressBar1.setValue(i);
                            jLabel24.setText(i + " %");

                            if (i <= 18) {
                                //                            Thread.sleep(500);
                                Thread.sleep(50);

                            } else if (i <= 83) {
                                //                            Thread.sleep(1000);
                                Thread.sleep(100);

                            } else if (i <= 100) {
                                //                            Thread.sleep(200);
                                Thread.sleep(20);

                            }

                            if (i == 100) {
                                File dir = new File("C:/");
                                File Fol = new File("C:/Sankalpa");
                                File Fol1 = new File("C:/Sankalpa/summery.01");

                                Fol.mkdir();
                                Fol1.mkdir();

                                String months = "" + month;
                                String dates = "" + date;
                                String hours = "" + hour;
                                String mins = "" + min;
                                String secs = "" + sec;

                                if (month < 10) {
                                    months = "0" + month;
                                }

                                if (date < 10) {
                                    dates = "0" + date;
                                }

                                if (hour < 10) {
                                    hours = "0" + hour;
                                }

                                if (min < 10) {
                                    mins = "0" + min;
                                }

                                if (sec < 10) {
                                    secs = "0" + sec;
                                }

                                String s = year + "-" + months + "-" + dates + "_(" + hours + "-" + mins + "-" + secs + ")";
                                String c[] = dir.list();
                                for (String name : c) {
                                    if (name.equals("Program Files (x86)")) {
                                        String sql1 = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin/mysqldump -uroot -p1234 pegasus -r C:\\pegasusReports\\backups/" + s + ".sql";
                                        Runtime.getRuntime().exec(sql1);
                                    } else if (name.equals("ProgramFiles")) {
                                        String sql1 = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin/mysqldump -uroot -p1234 pegasus -r C:\\pegasusReports\\backups/" + s + ".sql";
                                        Runtime.getRuntime().exec(sql1);
                                    }

                                }

                                jProgressBar1.setVisible(false);
                                jLabel24.setVisible(false);
                                jLabel25.setVisible(false);
                                m0(199);
                                Thread.sleep(500);

                                jLabel26.setVisible(true);
                                jLabel27.setVisible(true);
                                jLabel28.setVisible(true);
                                jLabel29.setVisible(true);
                                jLabel31.setVisible(true);
                                jLabel32.setVisible(true);
                                jLabel33.setVisible(true);
                                jLabel34.setVisible(true);
                                jProgressBar1.setVisible(false);

                                GetFolderSize SizeF = new GetFolderSize();
                                jLabel34.setText(SizeF.size("Akash"));
                                jLabel32.setText(SizeF.m(1992));

                                String Lname = "No backup found ...";
                                File f0 = new File("C:/Sankalpa/summery.01");
                                String list0[] = f0.list();
                                for (String name : list0) {
                                    Lname = name;
                                }

                                File f1 = new File("C:/Sankalpa/summery.01/" + Lname + "");

                                double bytes = f1.length();
                                double kilobytes = (bytes / 1024);
                                double megabytes0 = Math.round(kilobytes / 1024);
                                double megabytes = (kilobytes / 1024);
                                double gigabytes0 = Math.round(megabytes / 1024);
                                double gigabytes = (megabytes / 1024);

                                StringBuffer kb0 = null;
                                if (gigabytes0 != 0.0) {
                                    kb0 = new StringBuffer(gigabytes + "");

                                } else if (megabytes0 != 0.0) {
                                    kb0 = new StringBuffer(megabytes + "");

                                } else {
                                    kb0 = new StringBuffer(kilobytes + "");

                                }

                                int index1 = kb0.indexOf(".");
                                String kb1 = kb0.substring(0, index1);
                                String kb2 = kb0.substring(index1 + 1);
                                String kb3[] = kb2.split("");

                                if (gigabytes0 != 0.0) {
                                    jLabel29.setText(kb1 + "." + kb3[1] + "" + kb3[2] + " GB");

                                } else if (megabytes0 != 0.0) {
                                    jLabel29.setText(kb1 + "." + kb3[1] + "" + kb3[2] + " MB");

                                } else {
                                    jLabel29.setText(kb1 + "." + kb3[1] + " KB");

                                }

                                String s0[] = Lname.split("_");

                                StringBuffer sb1 = new StringBuffer(s0[1]);
                                int index = sb1.indexOf("(");
                                String P2 = sb1.substring(index + 1);

                                StringBuffer sb2 = new StringBuffer(P2);
                                int index2 = sb2.indexOf(")");
                                String P3 = sb2.substring(0, index2);

                                String ss[] = P3.split("-");
                                int H = Integer.parseInt(ss[0]);
                                int h = H;
                                String ap = " PM";

                                if (H > 50) {
                                    h = (H - 50) - 12;

                                }

                                if (H < 50) {
                                    ap = " AM";

                                }

                                jLabel27.setText(s0[0] + "  @  " + h + "." + ss[1] + "." + ss[2] + ap);
                                jButton10.setEnabled(true);

                            }

                        } catch (Exception e) {
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {

                public void run() {
                    System.gc();
                    for (int i = 0; i
                            < 101; i++) {
                        try {
                            if (labe3 == 1) {
                                jLabel25.setText("Checking Database .");
                                Thread.sleep(500);
                                jLabel25.setText("Checking Database ..");
                                Thread.sleep(500);
                                jLabel25.setText("Checking Database ...");
                                Thread.sleep(500);
                                jLabel25.setText("Checking Database ....");
                                Thread.sleep(500);

                            }

                            if (labe3 != 1 & labe4 == 2) {
                                jLabel25.setText("Createing Backup .");
                                Thread.sleep(500);
                                jLabel25.setText("Createing Backup ..");
                                Thread.sleep(500);
                                jLabel25.setText("Createing Backup ...");
                                Thread.sleep(500);
                                jLabel25.setText("Createing Backup ....");
                                Thread.sleep(500);

                            }

                            if (labe4 != 2 & labe5 == 3) {
                                jLabel25.setText("Saving Backup .");
                                Thread.sleep(500);
                                jLabel25.setText("Saving Backup ..");
                                Thread.sleep(500);
                                jLabel25.setText("Saving Backup ...");
                                Thread.sleep(500);
                                jLabel25.setText("Saving Backup ....");
                                Thread.sleep(500);

                            }

                        } catch (Exception e) {
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {

                public void run() {
                    System.gc();
                    while (labe2 != 254) {
                        try {

                            if (jProgressBar1.getValue() == 100) {
                                jLabel30.setVisible(true);
                            }

                            if (labe0 == 199) {
                                for (int i = 0; i < 154; i++) {
                                    int iR = 102 + i;
                                    int iB = 101 + i;
                                    jLabel30.setForeground(new java.awt.Color(iR, 0, iB));
                                    Thread.sleep(5);
                                    m1(i);

                                }

                            }

                            if (labe1 == 153) {
                                for (int i = 0; i < 255; i++) {
                                    int iG = i;
                                    jLabel30.setForeground(new java.awt.Color(255, iG, 254));
                                    Thread.sleep(5);
                                    m2(iG);

                                }

                            }

                            if (labe2 == 254) {
                                jLabel30.setVisible(false);
                                jButton2.setVisible(true);

                            }

                        } catch (Exception e) {
                        }
                    }

                }
            ;
            }).start();

            labe0 = 0;
            labe1 = 0;
            labe2 = 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained

    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost

    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        //jTextField5.grabFocus();
    }//GEN-LAST:event_jTextField9ActionPerformed

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

    private void jTable10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MousePressed

    }//GEN-LAST:event_jTable10MousePressed

    private void jTable10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable10KeyPressed
        if (evt.getKeyCode() == 10) {
            try {
                if (jDateChooser1.getDate() == null) {

                    JOptionPane.showMessageDialog(null, "Please select the date");
                    jTable10.setVisible(false);
                    jScrollPane6.setVisible(false);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt1 = jDateChooser1.getDate();
                    String stringdate1 = sdf.format(dt1);

                    int Row = jTable10.getSelectedRow();
                    jTextField9.setText(jTable10.getValueAt(Row, 0).toString());
                    String id = jTable10.getValueAt(Row, 1).toString();
                    jTextField1.setText(id);

                    int searchExcisting = SummeryController.searchsummeryByDateAndId(stringdate1, id);
                    if (searchExcisting == 0) {
                        JOptionPane.showMessageDialog(null, "Today " + jTextField9.getText() + " summery is not added");

                        jScrollPane6.setVisible(false);
                        jTable10.setVisible(false);
                        jButton1.setEnabled(false);
                    } else {
                        jTable10.setVisible(false);
                        jScrollPane6.setVisible(false);
                        jButton1.setEnabled(true);
                        jButton1.grabFocus();
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTable10KeyPressed

    private void jTable10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable10KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jTextField9.getText().length() != 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dt1 = jDateChooser1.getDate();
                String stringdate1 = sdf.format(dt1);

                boolean deleteSumline = Summery_lineController.deleteSummery_line(stringdate1, Integer.parseInt(jTextField1.getText()));
                boolean deleteSum = SummeryController.deleteAllSummeryByDateAndId(stringdate1, jTextField1.getText());

                JOptionPane.showMessageDialog(null, jTextField9.getText() + " summery is deleted");
                jTextField9.setText("");
                jTextField1.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please select the center");
            }
        } catch (SQLException ex) {
            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        jTextField9.setText("");
        jProgressBar1.setVisible(false);
        jLabel30.setVisible(false);
        jTextField1.setText("");
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = jDateChooser2.getDate();
        Date dt2 = jDateChooser3.getDate();
        String stringdate1 = sdf.format(dt1);
        String stringdate2 = sdf.format(dt2);

        if (jDateChooser2.getDate() == null && jDateChooser3.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Please select the Dates");
        } else if (jDateChooser2.getDate() == null && jDateChooser3.getDate() != null) {
            JOptionPane.showMessageDialog(null, "Please select the Wrong date");
        } else if (jDateChooser2.getDate() != null && jDateChooser3.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Please select the Correct date");
        } else if (jDateChooser2.getDate() != null && jDateChooser3.getDate() != null) {

            try {
                int searchExcisting = SummeryController.searchsummeryByDate(stringdate1);
                if (searchExcisting == 0) {

                } else {
                    int updateAllDateSumLine = Summery_lineController.UpdateAllSummeryLineDate(stringdate1, stringdate2);
                    int updateAllDateSum = SummeryController.UpdateAllSummeryDate(stringdate1, stringdate2);

                    int updateAllDateGenSumLine = GeneralSummeryLineController.UpdateAllGenSummeryLineDate(stringdate1, stringdate2);
                    int updateAllDateGenSum = General_summeryController.UpdateAllGenSummeryDate(stringdate1, stringdate2);
                }
                JOptionPane.showMessageDialog(null, "Correct");
            } catch (SQLException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            FileWriter fw = new FileWriter("Network.txt");
            String name = "niroth";
            fw.write(name);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                settings dialog = new settings(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable10;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
