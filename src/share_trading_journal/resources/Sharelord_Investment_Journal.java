/*
 * Sharelord_Investment_Journal.java
 *
 * Created on June 4, 2014, 5:20 PM
 */

package share_trading_journal.resources;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author  Ravi Patel
 */
public class Sharelord_Investment_Journal extends javax.swing.JFrame {
    
   
    String sc="";
    String entrydate=null,stockcode=null,comonth=null,pomonth=null,delscode="";
    int stock=0,delcnt=0;
    double breakeven=0.0,purchaseprice=0.0,costrike=0.0,copremium=0.0,postrike=0.0,popremium=0.0,brokerage=0.0,totalnetprofit=0.0;
    String chartpath=null;
    boolean flag=false;
    Connection con;
    ArrayList<Integer> counter = new ArrayList();
    static
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        }catch
                (Exception e){}
    }
    public Sharelord_Investment_Journal()
    {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        try
        {
            //jXImagePanel1.setImage(ImageIO.read(new File("images/sharelordbanner1.jpg")));
            jLabel2.setIcon(new ImageIcon("images/sharelordbanner1.jpg"));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Logo Image Not Found!!!");
        }
    }
    public void fillData()
    {
        try
        {
            con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           
            Statement st = con.createStatement();
            int row,cnt=0;
            ResultSet rs;
            if(jTabbedPane1.getSelectedIndex()==0)
            {
                rs = st.executeQuery("select count(*) from new_trade where Status='Open'");
                rs.next();
                row = rs.getInt(1);
                rs = st.executeQuery("Select * from new_trade where Status='Open'");
                flag=false;
            }
            else
            {
                rs = st.executeQuery("select count(*) from new_trade where Status='Close'");
                rs.next();
                row = rs.getInt(1);
                rs = st.executeQuery("Select * from new_trade where Status='Close'");
                flag=true;
            }
            Object [][]data = new Object[row][14];
        
            while(rs.next())
            {
                data[cnt][0]=rs.getString("Status");
                data[cnt][1]=rs.getString("Stock_Code");
                data[cnt][2]=rs.getInt("Stock");
                data[cnt][3]="$"+String.format("%,.2f",rs.getDouble("Break_Even"));
                double MDRisk = rs.getDouble("Break_Even")-rs.getDouble("Strike_Price_PO");
                data[cnt][4]="$"+String.format("%,.2f",MDRisk);
                data[cnt][5]=String.format("%,.2f",(MDRisk/rs.getDouble("Break_Even"))*100)+"%";
                data[cnt][6]=rs.getString("Outcome");
                data[cnt][7]=rs.getDouble("Strike_Price_CO")-rs.getDouble("Break_Even");
                data[cnt][8]=(rs.getDouble("Strike_Price_CO")-rs.getDouble("Break_Even"))*rs.getInt("Stock");
                data[cnt][9]=String.format("%,.2f",((rs.getDouble("Strike_Price_CO")-rs.getDouble("Break_Even"))/rs.getDouble("Stock_Purchase_Price"))*100)+"%";

                data[cnt][10]=rs.getDouble("Brokerage")+getBrokerage(data[cnt][1].toString()+rs.getInt("cnt"));
        
                //totalnetprofit+=Double.parseDouble(data[cnt][11].toString());
                if(rs.getString("Status").equals("Close"))
                {
                    double netprofit = rs.getDouble("Stock_Sold_Price");
                    netprofit -= rs.getDouble("Break_Even");
                    double tnetprofit = netprofit;
                    netprofit -= Double.parseDouble(data[cnt][10].toString()) / rs.getInt("Stock");

                    data[cnt][11] = netprofit;
                    tnetprofit *= rs.getInt("Stock");
                    data[cnt][12] = tnetprofit - Double.parseDouble(data[cnt][10].toString());
                    //totalnetprofit+=Double.parseDouble(data[cnt][12].toString());
                    
                    if(rs.getString("Outcome").equals("Close Out Position"))
                        data[cnt][11] = "$"+String.format("%,.2f", rs.getDouble("Stock_Sold_Price")-rs.getDouble("Break_Even"));
                    else
                        data[cnt][11] = "$" + String.format("%,.2f", data[cnt][11]);
                    
                    data[cnt][12]=String.format("$%,.2f",data[cnt][12]);
                }
                data[cnt][7]="$"+String.format("%,.2f",data[cnt][7]);
                data[cnt][10]="$"+String.format("%,.2f",data[cnt][10]);
                data[cnt][8]=String.format("$%,.2f",data[cnt][8]);

                //JOptionPane.showMessageDialog(this,"HI");
                counter.add(cnt,rs.getInt("cnt"));
                //JOptionPane.showMessageDialog(this,"HEllo");
                cnt++;
            }
            final boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false, false, false, false, false, false, false, false, true
        };
            jTable1.setModel(new DefaultTableModel(data,new String [] {
        "Status", "Stock Code", "Qty", "Break Even", "Trade Risk $", "Trade Risk %", "Outcome", " Profit Per Share", "Gross Profit", "ROI %", "Brokerage", "Net Profit","Total Net Profit", " "
    }){public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
        }});

            jTable1.getColumn(" ").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn(" ").setCellEditor(new ButtonEditor(new JCheckBox()));
            st.close();
            rs.close();
            calculateNetGrowth();
            fillBal();
            totalnetprofit=0.0;
            con.close();
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e);
            this.dispose();
        }
        TableColumnModel tcm = jTable1.getColumnModel();
        tcm.getColumn(2).setPreferredWidth(25);
        tcm.getColumn(0).setPreferredWidth(25);
        tcm.getColumn(3).setPreferredWidth(55);
        tcm.getColumn(4).setPreferredWidth(65);
        tcm.getColumn(5).setPreferredWidth(70);
        tcm.getColumn(1).setPreferredWidth(140);
        tcm.getColumn(6).setPreferredWidth(111);
        tcm.getColumn(8).setPreferredWidth(60);
        tcm.getColumn(9).setPreferredWidth(40);
        tcm.getColumn(10).setPreferredWidth(50);
        tcm.getColumn(11).setPreferredWidth(55);
        tcm.getColumn(12).setPreferredWidth(80);
        tcm.getColumn(13).setPreferredWidth(280);
    }

    private double getBrokerage(String sc)
    {
        try
        {
        ResultSet rs = con.createStatement().executeQuery("select sum(Brokerage) from intervention where Stock_Code='"+sc+"'");

        rs.next();
        return rs.getDouble(1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Hello"+e);
        }
        return 0.0;
    }
    public void calculateNetGrowth()
    {
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Stock,Break_Even,Brokerage,Stock_Code,Stock_Sold_Price,cnt from new_trade where Status='Close'");

            while(rs.next())
            {
                double netprofit = rs.getDouble("Stock_Sold_Price");
                netprofit -= rs.getDouble("Break_Even");
                double tnetprofit = netprofit;
                netprofit -= (rs.getDouble("Brokerage")+getBrokerage(rs.getString("Stock_Code")+rs.getInt("cnt"))) / rs.getInt("Stock");

                tnetprofit *= rs.getInt("Stock");
                totalnetprofit += tnetprofit - (rs.getDouble("Brokerage")+getBrokerage(rs.getString("Stock_Code")+rs.getInt("cnt")));
            }
            rs.close();
            st.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnnewtread = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblrunbal = new javax.swing.JLabel();
        lblnetgrowth = new javax.swing.JLabel();
        btneditrunbal = new javax.swing.JButton();
        lblsbal = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jXHyperlink1 = new org.jdesktop.swingx.JXHyperlink();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sharelord Investment Journal");
        setIconImage(new ImageIcon("images/Sharelord_logo.png").getImage());
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Stock Code", "Qty", "Break Even", "Risk", "%", "Outcome", "Per Share", "Total", "ROI%", "Brokerage", "Net Profit", "Total Net Profit", "Links"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(49);
        jScrollPane1.setViewportView(jTable1);

        btnnewtread.setFont(new java.awt.Font("Arial", 1, 14));
        btnnewtread.setText("New Investment");
        btnnewtread.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnnewtread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewtreadActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("All Investments");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel15.setText("Starting Balance");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel14.setText("Running Balance");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel16.setText("Net Growth");

        lblrunbal.setFont(new java.awt.Font("Arial", 0, 14));
        lblrunbal.setText("$0.00");

        lblnetgrowth.setFont(new java.awt.Font("Arial", 0, 14));
        lblnetgrowth.setText("0.00%");

        btneditrunbal.setFont(new java.awt.Font("Arial", 0, 14));
        btneditrunbal.setText("Edit");
        btneditrunbal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditrunbalActionPerformed(evt);
            }
        });

        lblsbal.setFont(new java.awt.Font("Arial", 0, 14));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblsbal)
                            .addComponent(lblrunbal)
                            .addComponent(lblnetgrowth))
                        .addGap(143, 143, 143))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btneditrunbal)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblsbal)
                    .addComponent(btneditrunbal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblrunbal))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblnetgrowth))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 14));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1211, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Open Trades", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1211, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Closed Trades", jPanel3);

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel3.setText("Sharelord®. The Most Powerful Cashflow Strategy On The Planet...");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel4.setForeground(new java.awt.Color(0, 204, 102));
        jLabel4.setText("Get Your FREE Getting Started Pack:");

        jXHyperlink1.setText("www.DynamicProfitsOnline.com");
        jXHyperlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(jLabel1)
                            .addGap(162, 162, 162)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnnewtread))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(491, 491, 491)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXHyperlink1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnnewtread)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jXHyperlink1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnewtreadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewtreadActionPerformed
        // TODO add your handling code here:
        NewTrade nt = new NewTrade();
        nt.pack();
        nt.setLocationRelativeTo(null);
        nt.setVisible(true);
    }//GEN-LAST:event_btnnewtreadActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        fillData();
    }//GEN-LAST:event_formWindowGainedFocus
    public void fillBal()
    {
        double tmp = 0.0;
         try
         {
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("select * from balance");
             
             rs.next();
             
             lblsbal.setText("$"+String.format("%,.2f",rs.getDouble("Start_Bal")));
             //if (flag) {
                 tmp = (rs.getDouble("Start_Bal") + totalnetprofit);
                 lblrunbal.setText("$" + String.format("%,.2f", tmp));
                 lblnetgrowth.setText(String.format("%.2f", ((tmp - rs.getDouble("Start_Bal")) / rs.getDouble("Start_Bal")) * 100) + "%");
             //}
             st.close();
             rs.close();
         }
         catch(Exception e)
         {JOptionPane.showMessageDialog(this,e);
         }
    }
    private void btneditrunbalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditrunbalActionPerformed
        // TODO add your handling code here:
        try
        {
            double bal = Double.parseDouble(JOptionPane.showInputDialog("Enter Starting Balance :"));
            if(bal>=0)
            {
                try
                {
                    con = DriverManager.getConnection("jdbc:derby:database/share_trading");
                    PreparedStatement pst = con.prepareStatement("update balance set Start_Bal=?");
                    pst.setDouble(1,bal);
                    pst.executeUpdate();

                    pst.close();
                    fillBal();
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(this,e);
                }
                JOptionPane.showMessageDialog(this,"Starting Balance Updated...");
            }
        }
        catch(NullPointerException n)
        {
        }
    }//GEN-LAST:event_btneditrunbalActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        fillData();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jXHyperlink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink1ActionPerformed
        // TODO add your handling code here
        try
        {
        Desktop.getDesktop().browse(new URI("http://www.dynamicprofitsonline.com"));
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"Problem occured to visit website...");
        }

    }//GEN-LAST:event_jXHyperlink1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sharelord_Investment_Journal().setVisible(true);
            }
        });
    }
    class ButtonEditor extends DefaultCellEditor {

    JLabel lblview,lblroll,lblclose,lblrent,lblinsure,lbldelete;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    lblview = new JLabel(new ImageIcon("images/view.png"));
    lblroll = new JLabel(new ImageIcon("images/roll.png"));
    lblclose = new JLabel(new ImageIcon("images/close.png"));
    lblrent = new JLabel(new ImageIcon("images/rent.png"));
    lblinsure = new JLabel(new ImageIcon("images/insure.png"));
    lbldelete = new JLabel(new ImageIcon("images/delete.png"));
    
    lblview.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent me)
        {
            ViewTrade v = new ViewTrade(sc,flag,delscode);
            v.pack();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
        }
        public void mouseEntered(MouseEvent me)
        {
            lblview.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    });
    lblroll.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent me)
        {
            Rolling r = new Rolling(sc,delscode);
            r.pack();
            r.setLocationRelativeTo(null);
            r.setVisible(true);
        }
        public void mouseEntered(MouseEvent me)
        {
            lblroll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
    });
    lblclose.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent me)
        {
            CloseOutPosition c = new CloseOutPosition(sc,delscode);
            c.pack();
            c.setLocationRelativeTo(null);
            c.setVisible(true);
            //jTabbedPane1.setSelectedIndex(1);
        }
        public void mouseEntered(MouseEvent me)
        {
            lblclose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
    });
    lblrent.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent me)
        {
               RentSellCallOption r = new RentSellCallOption(sc,delscode);
               r.pack();
               r.setLocationRelativeTo(null);
               r.setVisible(true);
        }
        public void mouseEntered(MouseEvent me)
        {
            lblrent.setCursor(new Cursor(Cursor.HAND_CURSOR));
           
        }
    });
    lblinsure.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent me)
        {
                InsureBuyPutOption i = new InsureBuyPutOption(sc,delscode);
                i.pack();
                i.setLocationRelativeTo(null);
                i.setVisible(true);
        }
        public void mouseEntered(MouseEvent me)
        {
            lblinsure.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
    });
    lbldelete.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent me)
        {
            if(JOptionPane.showConfirmDialog(null,"Are You Sure to delete?")==0)
            {
                Utility.deleteTrade(delscode,counter.get(delcnt));
                JOptionPane.showMessageDialog(null,"Trade Deleted Successfully...");
            }
        }
        public void mouseEntered(MouseEvent me)
        {
            lbldelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
      JPanel p = new JPanel();
      p.setBackground(Color.WHITE);
      delscode = table.getValueAt(row, 1).toString();
      delcnt = row;
      Utility.cnt=counter.get(row);
      sc = table.getValueAt(row, 1).toString()+counter.get(row);
      
    if(table.getValueAt(row,0).equals("Open"))
    {
        lblview.setToolTipText("View");
        lblroll.setToolTipText("Roll");
        lblclose.setToolTipText("Close");
        lblrent.setToolTipText("Rent");
        lblinsure.setToolTipText("Insure");
        lbldelete.setToolTipText("Delete");
        p.add(lblview);
        p.add(lblroll);
        p.add(lblclose);
        p.add(lblrent);
        p.add(lblinsure);
        p.add(lbldelete);
        return p;
      }
    p.add(lblview);
    p.add(lbldelete);
    return p;
  }

  public Object getCellEditorValue() {
        return null;
  }

  public boolean stopCellEditing() {
        return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
class ButtonRenderer extends JPanel implements TableCellRenderer {
JLabel lblview,lblroll,lblclose,lblrent,lblinsure,lbldelete;
  public ButtonRenderer() {
    lblview = new JLabel(new ImageIcon("images/view.png"));
    lblroll = new JLabel(new ImageIcon("images/roll.png"));
    lblclose = new JLabel(new ImageIcon("images/close.png"));
    lblrent = new JLabel(new ImageIcon("images/rent.png"));
    lblinsure = new JLabel(new ImageIcon("images/insure.png"));
    lbldelete = new JLabel(new ImageIcon("images/delete.png"));
    
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
      setBackground(Color.WHITE);
     if(table.getValueAt(row,0)!=null)
    if(table.getValueAt(row,0).equals("Open"))
    {
        
        add(lblview);
        add(lblroll);
        add(lblclose);
        add(lblrent);
        add(lblinsure);
        add(lbldelete);
        return this;
      }

      add(lblview);
     add(lbldelete);
     return this;
  }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneditrunbal;
    private javax.swing.JButton btnnewtread;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink1;
    private javax.swing.JLabel lblnetgrowth;
    private javax.swing.JLabel lblrunbal;
    private javax.swing.JLabel lblsbal;
    // End of variables declaration//GEN-END:variables
    
}