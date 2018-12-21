/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import pegasus.Controller.SummeryController;
import pegasus.Controller.Summery_lineController;

/**
 *
 * @author Chamil123
 */
public class AdminDashPanel extends javax.swing.JPanel {

    int rowId;

    /**
     * Creates new form addCenter
     */
    public AdminDashPanel() {
        initComponents();

        loadChart();
        loadDailySummeryValues();

    }

    private void loadDailySummeryValues() {

        try {
            Date d = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currnetDate = dateFormat.format(d);

            double fullCollection = SummeryController.searchSumFullCollectionByCurrentdate(currnetDate);
            jLabel4.setText("" + fullCollection);

            double totalPayments = SummeryController.searchSumChitPayementsByCurrentdate(currnetDate);
            jLabel10.setText("" + totalPayments);

            double totalExpenses = SummeryController.searchSumExpensesByCurrentdate(currnetDate);
            jLabel14.setText("" + totalExpenses);

            double totalIncome = SummeryController.searchIncomeByCurrentdate(currnetDate);
            jLabel17.setText("" + totalIncome);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class ClockListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Date d;
            DateFormat dateFormat;
            SimpleDateFormat sdf;
            SimpleDateFormat sdf1;
            String date;
            d = new Date();
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            sdf = new SimpleDateFormat("hh:mm:ss");
            sdf1 = new SimpleDateFormat("a");
//            jLabel5.setText(sdf.format(d));

            date = dateFormat.format(d);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(236, 239, 244));
        setMaximumSize(new java.awt.Dimension(1150, 590));
        setMinimumSize(new java.awt.Dimension(1150, 590));
        setPreferredSize(new java.awt.Dimension(1150, 590));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Dashboard");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1086, 523));
        jPanel1.setMinimumSize(new java.awt.Dimension(1086, 523));
        jPanel1.setPreferredSize(new java.awt.Dimension(1086, 523));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("Add cener");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1487, -1, -1));

        jPanel14.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1060, 410));

        jPanel2.setBackground(new java.awt.Color(16, 89, 108));
        jPanel2.setMaximumSize(new java.awt.Dimension(246, 93));
        jPanel2.setMinimumSize(new java.awt.Dimension(246, 93));
        jPanel2.setPreferredSize(new java.awt.Dimension(246, 93));
        jPanel2.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(13, 71, 86));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/currency.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(0, 0, 90, 93);

        jPanel10.setMaximumSize(new java.awt.Dimension(210, 3));
        jPanel10.setMinimumSize(new java.awt.Dimension(210, 3));
        jPanel10.setPreferredSize(new java.awt.Dimension(210, 3));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel10);
        jPanel10.setBounds(40, 60, 210, 3);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DAILY FULL COLLECTIONS");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(100, 10, 160, 14);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INCREASE IN DAILY");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(100, 70, 130, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("125.025");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(100, 30, 140, 30);

        jPanel3.setBackground(new java.awt.Color(185, 204, 50));
        jPanel3.setMaximumSize(new java.awt.Dimension(246, 93));
        jPanel3.setMinimumSize(new java.awt.Dimension(246, 93));
        jPanel3.setPreferredSize(new java.awt.Dimension(246, 93));
        jPanel3.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(148, 163, 40));
        jPanel7.setMaximumSize(new java.awt.Dimension(94, 93));
        jPanel7.setMinimumSize(new java.awt.Dimension(94, 93));
        jPanel7.setPreferredSize(new java.awt.Dimension(94, 93));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ping.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel7);
        jPanel7.setBounds(0, 0, 94, 93);

        jPanel11.setMaximumSize(new java.awt.Dimension(210, 3));
        jPanel11.setMinimumSize(new java.awt.Dimension(210, 3));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel11);
        jPanel11.setBounds(40, 60, 210, 3);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("INCREASE IN DAILY");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(100, 70, 130, 14);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DAILY CHIT PAYMENTS");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(100, 10, 150, 14);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("125.025");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(100, 30, 140, 30);

        jPanel4.setBackground(new java.awt.Color(221, 75, 57));
        jPanel4.setMaximumSize(new java.awt.Dimension(246, 84));
        jPanel4.setMinimumSize(new java.awt.Dimension(246, 84));
        jPanel4.setPreferredSize(new java.awt.Dimension(246, 84));
        jPanel4.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(177, 60, 46));
        jPanel8.setMaximumSize(new java.awt.Dimension(94, 93));
        jPanel8.setMinimumSize(new java.awt.Dimension(94, 93));
        jPanel8.setPreferredSize(new java.awt.Dimension(94, 93));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clip.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel8);
        jPanel8.setBounds(0, 0, 94, 93);

        jPanel12.setMaximumSize(new java.awt.Dimension(210, 3));
        jPanel12.setMinimumSize(new java.awt.Dimension(210, 3));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel12);
        jPanel12.setBounds(40, 60, 210, 3);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("125.025");
        jPanel4.add(jLabel14);
        jLabel14.setBounds(100, 30, 140, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("INCREASE IN DAILY");
        jPanel4.add(jLabel12);
        jLabel12.setBounds(100, 70, 130, 14);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DAILY EXPENSES");
        jPanel4.add(jLabel13);
        jLabel13.setBounds(100, 10, 140, 14);

        jPanel5.setBackground(new java.awt.Color(0, 192, 239));
        jPanel5.setMaximumSize(new java.awt.Dimension(246, 84));
        jPanel5.setMinimumSize(new java.awt.Dimension(246, 84));
        jPanel5.setPreferredSize(new java.awt.Dimension(246, 84));
        jPanel5.setLayout(null);

        jPanel9.setBackground(new java.awt.Color(0, 154, 191));
        jPanel9.setMaximumSize(new java.awt.Dimension(94, 93));
        jPanel9.setMinimumSize(new java.awt.Dimension(94, 93));
        jPanel9.setPreferredSize(new java.awt.Dimension(94, 93));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/recule.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.add(jPanel9);
        jPanel9.setBounds(0, 0, 94, 93);

        jPanel13.setMaximumSize(new java.awt.Dimension(210, 3));
        jPanel13.setMinimumSize(new java.awt.Dimension(210, 3));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel13);
        jPanel13.setBounds(40, 60, 210, 3);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("INCREASE IN DAILY");
        jPanel5.add(jLabel15);
        jLabel15.setBounds(100, 70, 130, 14);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("125.025");
        jPanel5.add(jLabel17);
        jLabel17.setBounds(100, 30, 140, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("DAILY INCOME");
        jPanel5.add(jLabel16);
        jLabel16.setBounds(100, 10, 140, 14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

    private void loadChart() {
        try {

            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
            List<String> sumerylineListList = SummeryController.searchSumFullCollectionBydate();

            DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
            for (int i = 0; i < sumerylineListList.size(); i++) {

                String arr[] = sumerylineListList.get(i).split(":");
                double value = Double.parseDouble(arr[0]);
                String date = arr[1];
                barChartData.addValue(value, "Total Full Collections", date);
            }

            List<String> sumeryChitPaymentList = SummeryController.searchSumChitPaymentBydate();

            for (int i = 0; i < sumeryChitPaymentList.size(); i++) {

                String arr[] = sumeryChitPaymentList.get(i).split(":");
                double value2 = Double.parseDouble(arr[0]);
                String date = arr[1];
                barChartData.addValue(value2, "Total Chit Payments", date);
            }

            final CategoryItemRenderer renderer = new BarRenderer();
            //    renderer.setLabelGenerator(generator);
            renderer.setItemLabelsVisible(true);

            Color color = new Color(0xb0, 0xde, 0x09, 190);
            //  Color color = new Color(177, 252, 47);
            renderer.setSeriesPaint(0, color);

            Color colors = new Color(121, 210, 158, 190);
            renderer.setSeriesPaint(1, colors);

            CategoryPlot plot = new CategoryPlot();
            plot.setDataset(barChartData);
            plot.setRenderer(renderer);

            plot.setDomainAxis(new CategoryAxis("Date"));
            plot.setRangeAxis(new NumberAxis("Cash"));

            plot.setOrientation(PlotOrientation.VERTICAL);
            plot.setRangeGridlinesVisible(true);
            plot.setDomainGridlinesVisible(true);

            // now create the second dataset and renderer...
            DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
            List<String> sumeryExpensesList = SummeryController.searchSumExpensesBydate();

            for (int i = 0; i < sumeryExpensesList.size(); i++) {

                String arr[] = sumeryExpensesList.get(i).split(":");
                double value2 = Double.parseDouble(arr[0]);
                String date = arr[1];
                dataset2.addValue(value2, "Total Expenses", date);
            }

            final CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
            plot.setDataset(1, dataset2);
            plot.setRenderer(1, renderer2);

            plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
            final JFreeChart chart = new JFreeChart(plot);
            chart.setTitle("Full collection and Expenses Bar Chart");
            // add the chart to a panel...
            final ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            jPanel14.removeAll();
            jPanel14.add(chartPanel, BorderLayout.CENTER);
            jPanel14.validate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
