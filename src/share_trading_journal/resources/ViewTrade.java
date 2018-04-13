/*
 * ViewTrade.java
 *
 * Created on June 4, 2014, 12:26 PM
 */
package share_trading_journal.resources;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ravi Patel
 */
public class ViewTrade extends javax.swing.JFrame {

    /**
     * Creates new form ViewTrade
     */
    String sc = "", ssc = "";
    boolean flag = false;
    double costrike = 0.0, postrike = 0.0, brokerage = 0.0;
    
    public ViewTrade(String sc, boolean flag, String ssc) {
        
        //add(btn);
        initComponents();
        
        this.sc = sc;
        this.ssc = ssc;
        this.flag = flag;

        renderMultipleChart();
        
        if (flag) {
            jLabel12.setVisible(false);
            jPanel2.setVisible(false);
        }
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        fillTable();
        fillLabels();
        
    }
    
    public void renderMultipleChart()
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Connection Problem ocuured");
                this.dispose();
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select charts from new_trade where Stock_Code='" + ssc + "' and cnt=" + Utility.cnt);
            
            rs.next();
            
            String charts = rs.getString(1);
            //JOptionPane.showMessageDialog(this, charts);
            if (!charts.equals("")) {
                String chartsName[] = charts.split(";");
                int noOfCharts = chartsName.length;
                JPanel panel = new JPanel();
                GridLayout gridLayout = new GridLayout((int) Math.ceil(noOfCharts / 10), 10);
                gridLayout.setHgap(8);
                gridLayout.setVgap(8);
                panel.setLayout(gridLayout);
                JButton btn[] = new JButton[noOfCharts];
                for (int i = 0; i < noOfCharts; i++) {
                    //JOptionPane.showMessageDialog(this, "charts/" + chartsName[i]+"_"+(i+1) + ".png");
                    final String filename = chartsName[i];
                    ImageIcon img = new ImageIcon(ImageIO.read(new File("charts/" + chartsName[i] + ".png")).getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                    btn[i] = new JButton();
                    btn[i].setIcon(img);
                    btn[i].setPreferredSize(new Dimension(120, 120));

                    btn[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                JFrame frm = new JFrame("Chart");
                                JScrollPane jsp = new JScrollPane(new JLabel(new ImageIcon(ImageIO.read(new File("charts/" + filename + ".png")))));
                                frm.add(jsp);
                                //frm.setLocationRelativeTo();
                                frm.setSize(1300, 750);
                                frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                frm.setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(ViewTrade.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                    panel.add(btn[i]);

                }
                jPanel6.setLayout(new BorderLayout());
                jPanel6.add(panel);

            }
            st.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
            
        
    }

    public void fillLabels() {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Connection Problem ocuured");
                this.dispose();
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from new_trade where Stock_Code='" + ssc + "' and cnt=" + Utility.cnt);

            while (rs.next()) {
                lblentrydate.setText(rs.getString("Entry_Date"));
                lblstock.setText(rs.getInt("Stock") + "");
                lblstockcode.setText(rs.getString("Stock_Code"));
                lblpurchaseprice.setText("$" + String.format("%,.2f", rs.getDouble("Stock_Purchase_Price")));
                if (!(rs.getDouble("Stock_Sold_Price") == 0.0)) {
                    lblSoldPrice.setText("$" + String.format("%,.2f", rs.getDouble("Stock_Sold_Price")));
                } else {
                    lblSoldPrice.setText("N/A");
                }

                lblpprofit.setText("$" + String.format("%,.2f", rs.getDouble("Strike_Price_CO") - (rs.getDouble("Break_Even") + (rs.getDouble("Brokerage") / (rs.getInt("Stock"))))));
                lblploss.setText("$" + String.format("%,.2f", rs.getDouble("Strike_Price_PO") - rs.getDouble("Break_Even") - ((rs.getDouble("Brokerage") + brokerage) / rs.getInt("Stock"))));
                brokerage = 0.0;
                lblcurrbrkevent.setText("$" + String.format("%,.2f", rs.getDouble("Break_Even")));
                lblTrueBreakEven.setText("$" + String.format("%,.2f", rs.getDouble("Break_Even") + (rs.getDouble("Brokerage") / (rs.getInt("Stock")))));

                lblcomonth.setText(rs.getString("Expire_Month_CO"));
                costrike = rs.getDouble("Strike_Price_CO");
                lblcostrikeprice.setText("$" + String.format("%,.2f", costrike));
                lblcopremium.setText("$" + String.format("%,.2f", rs.getDouble("Premium_CO")));

                lblpomonth1.setText(rs.getString("Expire_Month_PO"));
                postrike = rs.getDouble("Strike_Price_PO");
                lblpostrikeprice1.setText("$" + String.format("%,.2f", postrike));
                lblpopremium1.setText("$" + String.format("%,.2f", rs.getDouble("Premium_PO")));

                lblcoyield.setText(String.format("%.2f", (rs.getDouble("Premium_CO") / rs.getDouble("Stock_Purchase_Price")) * 100) + "%");
                lblcolleryield.setText(String.format("%.2f", ((rs.getDouble("Premium_CO") - rs.getDouble("Premium_PO")) / rs.getDouble("Stock_Purchase_Price")) * 100) + "%");
                lblrisk.setText(String.format("%.2f", ((rs.getDouble("Break_Even") - rs.getDouble("Strike_Price_PO")) / rs.getDouble("Stock_Purchase_Price")) * 100) + "%");
                txtComment.setText(rs.getString("Comment"));
            }

            st.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void fillTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Connection Problem ocuured");
                this.dispose();
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from intervention where Stock_Code='" + sc + "'");
            rs.next();
            int row = rs.getInt(1), cnt = 0;
            rs = st.executeQuery("Select * from intervention where Stock_Code='" + sc + "'");
            Object[][] data = new Object[row][10];

            while (rs.next()) {
                data[cnt][0] = rs.getString("Tra_Date");
                data[cnt][1] = rs.getString("Action");
                data[cnt][2] = rs.getString("Opt_Type");
                data[cnt][3] = rs.getString("Close_edate");
                data[cnt][4] = "$" + String.format("%,.2f", rs.getDouble("Close_strike"));

                if ((rs.getString("Action").equals("Roll") && rs.getString("Opt_Type").equals("Call")) || (rs.getString("Action").equals("Close Out Position") && rs.getString("Opt_Type").equals("Call"))) {
                    data[cnt][5] = "-$" + String.format("%,.2f", rs.getDouble("Close_premium"));
                } else {
                    data[cnt][5] = "$" + String.format("%,.2f", rs.getDouble("Close_premium"));
                }

                data[cnt][6] = rs.getString("Open_edate");
                data[cnt][7] = "$" + String.format("%,.2f", rs.getDouble("Open_strike"));

                if ((rs.getString("Action").equals("New  Position") && rs.getString("Opt_Type").equals("Put") || (rs.getString("Action").equals("Roll") && rs.getString("Opt_Type").equals("Put")))) {
                    data[cnt][8] = "-$" + String.format("%,.2f", rs.getDouble("Open_premium"));
                } else {
                    data[cnt][8] = "$" + String.format("%,.2f", rs.getDouble("Open_premium"));
                }

      
                data[cnt][9] = "$" + String.format("%,.2f", (rs.getDouble("Open_premium") - rs.getDouble("Close_premium")));

                if (!rs.getString("Action").equals("Close Out Position")) {
                    brokerage += rs.getDouble("Brokerage");
                }

                cnt++;
            }
            jTable1.setModel(new DefaultTableModel(data, new String[]{
                "Date", "Action", "Option Type", "Close Expiration Date", "Close Strike", "Close Premium", "Open Expiration Date", "Open Strike", "Open Premium", "Net Amount"
            }) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
            st.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePainter1 = new org.jdesktop.swingx.painter.ImagePainter();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblpomonth1 = new javax.swing.JLabel();
        lblpostrikeprice1 = new javax.swing.JLabel();
        lblpopremium1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblcoyield = new javax.swing.JLabel();
        lblcolleryield = new javax.swing.JLabel();
        lblrisk = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cmboutcome = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtbrokerage = new javax.swing.JTextField();
        btnsaveoutcome = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblcomonth = new javax.swing.JLabel();
        lblcostrikeprice = new javax.swing.JLabel();
        lblcopremium = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblentrydate = new javax.swing.JLabel();
        lblstockcode = new javax.swing.JLabel();
        lblcurrbrkevent = new javax.swing.JLabel();
        lblpurchaseprice = new javax.swing.JLabel();
        lblstock = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblpprofit = new javax.swing.JLabel();
        lblploss = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTrueBreakEven = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblSoldPrice = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        btnUpdate = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnUploadChart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Trade");
        setIconImage(new ImageIcon("images/Sharelord_logo.png").getImage());
        setResizable(false);

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setText("Interventions");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Expiry Month");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Strike Price");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Premium");

        lblpomonth1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblpostrikeprice1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblpopremium1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblpomonth1)
                    .addComponent(lblpopremium1)
                    .addComponent(lblpostrikeprice1))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblpomonth1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblpostrikeprice1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblpopremium1))
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Call Option");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("Chart");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("Call Option Yield %");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Coller Yield %");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Risk %");

        lblcoyield.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblcolleryield.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblrisk.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcoyield)
                    .addComponent(lblrisk)
                    .addComponent(lblcolleryield))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblcoyield))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblcolleryield))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblrisk))
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Trade Return / Risk");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Put Option");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("View Trade");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("# Stock");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Outcome");

        cmboutcome.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Assigned Call", "Assigned Put" }));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Brokerage");

        btnsaveoutcome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnsaveoutcome.setText("Save Outcome");
        btnsaveoutcome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveoutcomeActionPerformed(evt);
            }
        });
        btnsaveoutcome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnsaveoutcomeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtbrokerage, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmboutcome, javax.swing.GroupLayout.Alignment.LEADING, 0, 126, Short.MAX_VALUE)))
                        .addContainerGap(62, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsaveoutcome)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmboutcome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbrokerage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsaveoutcome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Entry Date");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Stock Code");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Gross Break Even");
        jLabel6.setToolTipText("");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Expiry Month");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Strike Price");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Premium");

        lblcomonth.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblcostrikeprice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblcopremium.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcomonth)
                    .addComponent(lblcostrikeprice)
                    .addComponent(lblcopremium))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblcomonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcostrikeprice)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblcopremium))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Stock Purchase Price");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Trade Outcome");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Action", "Option Type", "Close Expiration Date", "Close Strike", "Close Premium", "Open Expiration Date", "Open Strike", "Open Premium", "Net Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        lblentrydate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblstockcode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblcurrbrkevent.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblpurchaseprice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblstock.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("Potential Profit");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("Potential Loss");

        lblpprofit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblploss.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("True Break Even");

        lblTrueBreakEven.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTrueBreakEven.setToolTipText("");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setText("Stock Sold Price");

        lblSoldPrice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setText("Comment");

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jScrollPane3.setViewportView(txtComment);

        btnUpdate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnUpdate.setText("Update Comment");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        btnUploadChart.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnUploadChart.setText("Upload More Charts");
        btnUploadChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadChartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6))))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTrueBreakEven)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblcurrbrkevent)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel28))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblentrydate)
                                                    .addComponent(lblstockcode))
                                                .addGap(75, 75, 75)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel11))))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblpurchaseprice)
                                                    .addComponent(lblstock)
                                                    .addComponent(lblSoldPrice)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(101, 101, 101)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel27)
                                                    .addComponent(jLabel26))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblploss)
                                                    .addComponent(lblpprofit))))))))
                        .addGap(135, 135, 135)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel24)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1181, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUploadChart))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 974, Short.MAX_VALUE)
                            .addComponent(btnUpdate))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblentrydate)
                            .addComponent(jLabel11)
                            .addComponent(lblstock)
                            .addComponent(jLabel26)
                            .addComponent(lblpprofit))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblstockcode)
                            .addComponent(jLabel10)
                            .addComponent(lblpurchaseprice)
                            .addComponent(jLabel27)
                            .addComponent(lblploss))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblcurrbrkevent)
                                .addComponent(jLabel28)
                                .addComponent(lblSoldPrice)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblTrueBreakEven)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadChart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        Utility.updateComment(txtComment.getText(), ssc);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnsaveoutcomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnsaveoutcomeKeyPressed
        // TODO add your handling code here:
        saveOutcome();
    
    }//GEN-LAST:event_btnsaveoutcomeKeyPressed
    private void btnsaveoutcomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveoutcomeActionPerformed
        // TODO add your handling code here:
        saveOutcome();
    }//GEN-LAST:event_btnsaveoutcomeActionPerformed

    private void btnUploadChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadChartActionPerformed
        // TODO add your handling code here:
        uploadChart();
    }//GEN-LAST:event_btnUploadChartActionPerformed

    public void uploadChart() {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Connection Problem ocuured");
                this.dispose();
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select charts from new_trade where Stock_Code='" + ssc + "' and cnt=" + Utility.cnt);

            rs.next();

            String charts = rs.getString(1);
            //JOptionPane.showMessageDialog(this, charts);
            int nextCounter = 1;
            if(!charts.equals(""))
            {
                String chartsName[] = charts.split(";");
                //int noOfCharts = chartsName.length;
                String temp[] = chartsName[chartsName.length-1].split("_");
                nextCounter = Integer.parseInt(temp[1])+1;
            }
            String chartnm = sc +"_"+nextCounter;
            
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file.setFileFilter(new FileNameExtensionFilter("images", "jpg", "png"));
            int returnval = file.showOpenDialog(this);

            if (returnval == JFileChooser.APPROVE_OPTION) {
                
                st = con.createStatement();
                PreparedStatement pst = con.prepareStatement("update new_trade set charts=? where Stock_Code=? and cnt=?");

                pst.setString(1, (charts+chartnm+";"));
                pst.setString(2, ssc);
                pst.setInt(3, Utility.cnt);
                pst.executeUpdate();

                st.close();
                con.close();
                
                File f = file.getSelectedFile();
                String chartpath = f.getAbsolutePath();

                BufferedImage bi = ImageIO.read(new File(chartpath));
                ImageIO.write(bi, "png", new File("charts/" +chartnm+".png"));

                JOptionPane.showMessageDialog(this, "Chart Upload Successfully...");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chart does not Upload...");
            JOptionPane.showMessageDialog(this, e);
            
        }

    }
                                               

                                              

    public void saveOutcome()
    {
        if(!txtbrokerage.getText().equals(""))
        {
            Utility.updateBrokerage(Double.parseDouble(txtbrokerage.getText()),ssc);
            if(cmboutcome.getSelectedItem().toString().equals("Assigned Call"))
                  Utility.updateStockSoldPrice(costrike,ssc);
            else
                 Utility.updateStockSoldPrice(postrike,ssc);
            Utility.updateOutcome(cmboutcome.getSelectedItem().toString(),"Close",ssc);
            JOptionPane.showMessageDialog(this,"Outcome Saved Suuessfully...Trade is Closed.");
            this.dispose();
        }
        else
        {
               JOptionPane.showMessageDialog(this,"Please Enter brokerage first...");
        }
    }

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUploadChart;
    private javax.swing.JButton btnsaveoutcome;
    private javax.swing.JComboBox cmboutcome;
    private org.jdesktop.swingx.painter.ImagePainter imagePainter1;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblSoldPrice;
    private javax.swing.JLabel lblTrueBreakEven;
    private javax.swing.JLabel lblcolleryield;
    private javax.swing.JLabel lblcomonth;
    private javax.swing.JLabel lblcopremium;
    private javax.swing.JLabel lblcostrikeprice;
    private javax.swing.JLabel lblcoyield;
    private javax.swing.JLabel lblcurrbrkevent;
    private javax.swing.JLabel lblentrydate;
    private javax.swing.JLabel lblploss;
    private javax.swing.JLabel lblpomonth1;
    private javax.swing.JLabel lblpopremium1;
    private javax.swing.JLabel lblpostrikeprice1;
    private javax.swing.JLabel lblpprofit;
    private javax.swing.JLabel lblpurchaseprice;
    private javax.swing.JLabel lblrisk;
    private javax.swing.JLabel lblstock;
    private javax.swing.JLabel lblstockcode;
    private javax.swing.JTextArea txtComment;
    private javax.swing.JTextField txtbrokerage;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JLabel totalbrokeragecost;
}
