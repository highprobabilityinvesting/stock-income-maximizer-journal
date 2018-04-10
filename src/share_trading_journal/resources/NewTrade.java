/*
 * NewTrade.java
 *
 * Created on June 4, 2014, 10:14 AM
 */

package share_trading_journal.resources;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.sql.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.ImageIcon;
/**
 *
 * @author  Ravi Patel
 */
public class NewTrade extends javax.swing.JFrame {
    
    /** Creates new form NewTrade */
    String entrydate="",stockcode="",comonth="",pomonth="";
    int stock=0;
    double breakeven=0.0,purchaseprice=0.0,costrike=0.0,copremium=0.0,postrike=0.0,popremium=0.0,brokerage=0.0;
    String chartpath="",chartname="";
    boolean putavail=true;

    public NewTrade()
    {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtentrydate.setFormats("dd/MM/yyyy");
        dpcomonth.setFormats("MMM dd ''yy");
        dppomonth.setFormats("MMM dd ''yy");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtentrydate = new org.jdesktop.swingx.JXDatePicker();
        txtstockcode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        txtpurchaseprice = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtpopremium = new javax.swing.JTextField();
        txtpostrike = new javax.swing.JTextField();
        dppomonth = new org.jdesktop.swingx.JXDatePicker();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtcopremium = new javax.swing.JTextField();
        txtcostrike = new javax.swing.JTextField();
        dpcomonth = new org.jdesktop.swingx.JXDatePicker();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtbrokerage = new javax.swing.JTextField();
        lbltraderisk = new javax.swing.JLabel();
        lblcoyield = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnchartupload = new javax.swing.JButton();
        lblcolleryield = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();
        lblyrcal = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Trade");
        setIconImage(new ImageIcon("images/Sharelord_logo.png").getImage());
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("New Trade");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Entry Date");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Stock Code");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("# Stock");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Stock Purchase Price");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Expiry Month");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Strike Price");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Premium");

        txtpopremium.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpopremiumFocusLost(evt);
            }
        });

        txtpostrike.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpostrikeFocusLost(evt);
            }
        });

        dppomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dppomonthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtpopremium)
                    .addComponent(txtpostrike)
                    .addComponent(dppomonth, javax.swing.GroupLayout.PREFERRED_SIZE, 92, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(dppomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpostrike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtpopremium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Put Option");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Call Option");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Expiry Month");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Strike Price");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Premium");

        txtcopremium.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcopremiumFocusLost(evt);
            }
        });

        txtcostrike.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcostrikeFocusLost(evt);
            }
        });

        dpcomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpcomonthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcopremium)
                    .addComponent(txtcostrike)
                    .addComponent(dpcomonth, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dpcomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcostrike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcopremium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Call Option Yield");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Trade Risk % ");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Brokerage");

        txtbrokerage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtbrokerageFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtbrokerageFocusLost(evt);
            }
        });

        lbltraderisk.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbltraderisk.setText("0.00%");

        lblcoyield.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblcoyield.setText("0.00%");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Collar Yield");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Chart Upload");

        btnchartupload.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnchartupload.setText("Upload");
        btnchartupload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchartuploadActionPerformed(evt);
            }
        });
        btnchartupload.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnchartuploadKeyPressed(evt);
            }
        });

        lblcolleryield.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblcolleryield.setText("0.00%");

        btnsave.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        btnsave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnsaveKeyPressed(evt);
            }
        });

        btnclose.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnclose.setText("Cancel");
        btnclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncloseActionPerformed(evt);
            }
        });
        btnclose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncloseKeyPressed(evt);
            }
        });

        lblyrcal.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblyrcal.setForeground(new java.awt.Color(102, 0, 255));
        lblyrcal.setText("Yield_Risk_Calculation");
        lblyrcal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblyrcalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblyrcalMouseEntered(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Comment");

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jScrollPane2.setViewportView(txtComment);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel18)
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnchartupload)
                                    .addComponent(lblcolleryield)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtstockcode, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtpurchaseprice)
                                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(26, 26, 26)
                                .addComponent(txtbrokerage, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblcoyield, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbltraderisk))
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblyrcal)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel10)))
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstockcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtpurchaseprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblcoyield)
                        .addComponent(lblcolleryield)
                        .addComponent(jLabel15))
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbltraderisk)
                    .addComponent(lblyrcal))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtbrokerage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(btnchartupload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave)
                    .addComponent(btnclose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtpopremiumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpopremiumFocusLost
        // TODO add your handling code here:
}//GEN-LAST:event_txtpopremiumFocusLost

    private void txtpostrikeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpostrikeFocusLost
        // TODO add your handling code here:
        if(!Validate.isNumeric(txtpostrike.getText())) {
            txtpostrike.setText("");
        }
}//GEN-LAST:event_txtpostrikeFocusLost

    private void dppomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dppomonthActionPerformed
        // TODO add your handling code here:
        if(!dppomonth.getDate().toString().contains("Fri")) {
            JOptionPane.showMessageDialog(this,"Please Select Friday Only...");
            dppomonth.setDate(null);
        }
}//GEN-LAST:event_dppomonthActionPerformed

    private void txtcopremiumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcopremiumFocusLost
        // TODO add your handling code here:
        
}//GEN-LAST:event_txtcopremiumFocusLost

    private void txtcostrikeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcostrikeFocusLost
        // TODO add your handling code here:
        if(!Validate.isNumeric(txtcostrike.getText())) {
            txtcostrike.setText("");
        }
}//GEN-LAST:event_txtcostrikeFocusLost

    private void dpcomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpcomonthActionPerformed
        // TODO add your handling code here:
        if(!dpcomonth.getDate().toString().contains("Fri")) {
            JOptionPane.showMessageDialog(this,"Please Select Friday Only...");
            dpcomonth.setDate(null);
        }
}//GEN-LAST:event_dpcomonthActionPerformed

    private void btnchartuploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchartuploadActionPerformed
        // TODO add your handling code here:
        uploadChart();
}//GEN-LAST:event_btnchartuploadActionPerformed
    public void uploadChart()
    {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file.setFileFilter(new FileNameExtensionFilter("images", "jpg","png"));
        int returnval=file.showOpenDialog(this);

        if(returnval == JFileChooser.APPROVE_OPTION)
        {
            File f = file.getSelectedFile();
            chartpath = f.getAbsolutePath();
             if(!txtstockcode.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this,"Chart Uploaded...");
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Enter stock code first...");
                }
        }
    }
    public void noProblemChart(int c)
    {
        if(!chartpath.equals(""))
        {
            try
            {
               BufferedImage bi = ImageIO.read(new File(chartpath));
               chartname = txtstockcode.getText()+c+"_1";
               //JOptionPane.showMessageDialog(this, chartname);
               ImageIO.write(bi,"png",new File("charts/"+chartname+".png"));
            }
            catch(Exception e)
            {
                   JOptionPane.showMessageDialog(this,"Chart does not Upload...");
            }
        }
    }

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        saveTrade();
}//GEN-LAST:event_btnsaveActionPerformed

    private void btnchartuploadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnchartuploadKeyPressed
        // TODO add your handling code here:
        uploadChart();
    }//GEN-LAST:event_btnchartuploadKeyPressed

    private void btnsaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnsaveKeyPressed
        // TODO add your handling code here:
        saveTrade();
    }//GEN-LAST:event_btnsaveKeyPressed

    private void btncloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btncloseActionPerformed

    private void btncloseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncloseKeyPressed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btncloseKeyPressed

    private void txtbrokerageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbrokerageFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbrokerageFocusGained

    private void txtbrokerageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbrokerageFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtbrokerageFocusLost
    public void calculateyr()
    {
        putavail=true;
        try {
            purchaseprice = Double.parseDouble(txtpurchaseprice.getText());
            copremium = Double.parseDouble(txtcopremium.getText());
            if (!Validate.isNumeric(txtcopremium.getText())) {
                txtcopremium.setText("");
            } else {
                lblcoyield.setText(String.format("%.2f", (copremium / purchaseprice) * 100) + "%");
            }

            if (dppomonth.getDate() != null && !txtpostrike.getText().equals("") && !txtpopremium.getText().equals("")) {
                try {
                    popremium = Double.parseDouble(txtpopremium.getText());
                    postrike = Double.parseDouble(txtpostrike.getText());
                    if (!Validate.isNumeric(txtpopremium.getText())) {
                        txtpopremium.setText("");
                    } else {
                        lblcolleryield.setText(String.format("%.2f", ((copremium - popremium) / purchaseprice) * 100) + "%");
                        breakeven = (purchaseprice - copremium + popremium);
                        lbltraderisk.setText(String.format("%.2f", ((breakeven - postrike) / breakeven) * 100) + "%");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Enter numeric value...\n" + e);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
            } else {
                if (dppomonth.getDate() == null && txtpostrike.getText().equals("") && txtpopremium.getText().equals("")) {
                    putavail = false;
                    dppomonth.setDate(null);
                    postrike = 0.0;
                    popremium = 0.0;
                    lblcolleryield.setText("100.00%");
                    breakeven = (purchaseprice - copremium + 0.0);
                    lbltraderisk.setText("100.00%");
                } else {
                    String msg = "";

                    if (dppomonth.getDate() == null) {
                        msg += "\n->Put Month";
                    }
                    if (txtpostrike.getText().equals("")) {
                        msg += "\n->Put Strike";
                    }
                    if (txtpopremium.getText().equals("")) {
                        msg += "\n->Put Premium";
                    }
                    JOptionPane.showMessageDialog(this, "Following Fields are empty in Put Option:" + msg);
                }
            }
        } catch (NumberFormatException e) {
            if(txtpurchaseprice.getText().equals("") || txtcopremium.getText().equals(""))
                JOptionPane.showMessageDialog(this,"Empty Filleds stock purchase price or call option premium...");
            else
                JOptionPane.showMessageDialog(this, "Enter numeric value in stock purchase price and call option premium...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }
    private void lblyrcalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblyrcalMouseClicked
        // TODO add your handling code here:
        calculateyr();
    }//GEN-LAST:event_lblyrcalMouseClicked

    private void lblyrcalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblyrcalMouseEntered
        // TODO add your handling code here:
        lblyrcal.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblyrcalMouseEntered
    public int nextCnt()
    {
        try
        {
              Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery("select cnt from balance");
              rs.next();
              int cnt = rs.getInt(1);
              st.executeUpdate("update balance set cnt=cnt+1");
              st.close();
              con.close();
              return cnt;
        }
        catch(Exception e)
        {
                JOptionPane.showMessageDialog(this,e);
        }
        return (int)Math.random()*500000;
    }
    public void saveTrade()
    {
        if(txtpurchaseprice.getText().equals("") || Double.parseDouble(txtpurchaseprice.getText())<=0)
        {
            JOptionPane.showMessageDialog(this,"Stock Purchase Price Must be greater than 0...");
            txtpurchaseprice.setText("");
            return;
        }
        Validator v = new Validator();
            v.add(txtstockcode);
            v.add(txtstock);
            v.add(txtpurchaseprice);
            v.add(txtcostrike);
            v.add(txtcopremium);
            //v.add(txtpostrike);
            //v.add(txtpopremium);
            v.add(txtbrokerage);

        if(v.isValidate() && txtentrydate.getDate()!=null && dpcomonth.getDate()!=null)// && dppomonth.getDate()!=null
        {
            
            calculateyr();
            try
            {
                Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
                PreparedStatement pst = con.prepareCall("insert into new_trade values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

                entrydate = sd.format(txtentrydate.getDate());
                stockcode = txtstockcode.getText();
                stock = Integer.parseInt(txtstock.getText());

                sd=new SimpleDateFormat("MMM dd ''yy");
                comonth = sd.format(dpcomonth.getDate());
                costrike = Double.parseDouble(txtcostrike.getText());

                if(putavail)
                {
                    sd=new SimpleDateFormat("MMM dd ''yy");
                    pomonth = sd.format(dppomonth.getDate());
                }

                brokerage = Double.parseDouble(txtbrokerage.getText());


                    pst.setString(1,entrydate);
                    pst.setString(2,stockcode);
                    pst.setInt(3,stock);
                    Double.parseDouble(txtpurchaseprice.getText());
                    pst.setDouble(4,Double.parseDouble(String.format("%.4f",purchaseprice)));
                    pst.setString(5,comonth);
                    pst.setDouble(6,costrike);
                    pst.setDouble(7,copremium);
                    pst.setString(8,pomonth);
                    pst.setDouble(9,postrike);
                    pst.setDouble(10,popremium);
                    pst.setDouble(11,brokerage);
                    pst.setString(12,"");
                    pst.setDouble(13,breakeven);
                    pst.setDouble(14,0.0);
                    pst.setString(15,"Open");
                    pst.setString(16,"");
                    int no = nextCnt();
                    pst.setInt(17,no);
                    pst.setString(18,txtComment.getText());
                    
                    noProblemChart(no);
                    pst.setString(19,chartname+";");

                    pst.executeUpdate();
                    pst.close();

                    JOptionPane.showMessageDialog(this,"New Trade Created Successfully...");
                    addIntervention(con,no);
                    con.close();
                    this.dispose();
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this,e+"\nEnter Numeric Value...");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this,"Connection Problem Occured....");
            }

        }
        else
        {
           String msg = "";
                    if(txtentrydate.getDate()==null)
                        msg+="\n->Entry Date";
                    if(txtstock.getText().equals(""))
                        msg+="\n->#Stock";
                    if(txtstockcode.getText().equals(""))
                        msg+="\n->Stock Code";
                    if(txtpurchaseprice.getText().equals(""))
                        msg+="\n->Stock Purchase Price";
                    if(dpcomonth.getDate()==null)
                        msg+="\n->Call Month";
                    if(txtcostrike.getText().equals(""))
                        msg+="\n->Call Strike";
                    if(txtcopremium.getText().equals(""))
                        msg+="\n->Call Premium";
                    if(dppomonth.getDate()==null)
                        msg+="\n->Put Month";
                    if(txtpostrike.getText().equals(""))
                        msg+="\n->Put Strike";
                    if(txtpopremium.getText().equals(""))
                        msg+="\n->Put Premium";
                    if(txtbrokerage.getText().equals(""))
                        msg+="\n->Brokerage";

                    JOptionPane.showMessageDialog(this,"Following Fields are empty:"+msg);
        }

    }
    public void addIntervention(Connection con,int no)
    {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            String date = sd.format(new Date());
            PreparedStatement pst = con.prepareStatement("insert into intervention values(?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, date);
            pst.setString(2, "New  Position");
            pst.setString(3, "Call");
            pst.setString(4, "");
            pst.setDouble(5, 0.0);
            pst.setDouble(6, 0.0);
            sd = new SimpleDateFormat("MMM dd ''yy");
            pst.setString(7, comonth);
            pst.setDouble(8, costrike);
            pst.setDouble(9, copremium);
            //pst.setDouble(10,(Double.parseDouble(txtbrokerage.getText()))/2);
            pst.setDouble(10,0.0);
            pst.setString(11, stockcode+no);

            pst.executeUpdate();
            if(putavail)
            {
                pst.setString(3, "Put");
                pst.setString(7, pomonth);
                pst.setDouble(8, postrike);
                pst.setDouble(9, popremium);

                pst.executeUpdate();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }

    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchartupload;
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btnsave;
    private org.jdesktop.swingx.JXDatePicker dpcomonth;
    private org.jdesktop.swingx.JXDatePicker dppomonth;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblcolleryield;
    private javax.swing.JLabel lblcoyield;
    private javax.swing.JLabel lbltraderisk;
    private javax.swing.JLabel lblyrcal;
    private javax.swing.JTextArea txtComment;
    private javax.swing.JTextField txtbrokerage;
    private javax.swing.JTextField txtcopremium;
    private javax.swing.JTextField txtcostrike;
    private org.jdesktop.swingx.JXDatePicker txtentrydate;
    private javax.swing.JTextField txtpopremium;
    private javax.swing.JTextField txtpostrike;
    private javax.swing.JTextField txtpurchaseprice;
    private javax.swing.JTextField txtstock;
    private javax.swing.JTextField txtstockcode;
    // End of variables declaration//GEN-END:variables
    
}
