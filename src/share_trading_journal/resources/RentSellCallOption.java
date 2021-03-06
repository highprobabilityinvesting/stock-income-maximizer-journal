/*
 * RentSellCallOption.java
 *
 * Created on June 4, 2014, 9:54 AM
 */

package share_trading_journal.resources;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author  Ravi Patel
 */
public class RentSellCallOption extends javax.swing.JFrame {
    
    /** Creates new form RentSellCallOption */
    double curr_break_even=0.0;
    String sc="",ssc="";
    public RentSellCallOption(String sc,String ssc) {
        initComponents();
        this.sc=sc;
        this.ssc = ssc;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dpcomonth.setFormats("MMM dd ''yy");
        try
        {
            Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select Break_Even from new_trade where Stock_Code='"+ssc+"' and cnt="+Utility.cnt);

            while(rs.next())
            {
                 curr_break_even = rs.getDouble("Break_Even");
            }

            st.close();
            con.close();
        }
        catch(Exception e)
        {
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtbrokerage = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstrike = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtpremium = new javax.swing.JTextField();
        btnrent = new javax.swing.JButton();
        dpcomonth = new org.jdesktop.swingx.JXDatePicker();
        btncancel3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rent-Sell Call Option");
        setIconImage(new ImageIcon("images/Sharelord_logo.png").getImage());
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("Rent - Sell Call Option");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel15.setText("Sell Call Option");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel14.setText("Brokerage");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel4.setText("Month");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel5.setText("Strike");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel6.setText("Premium");

        btnrent.setFont(new java.awt.Font("Arial", 0, 18));
        btnrent.setText("Rent");
        btnrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrentActionPerformed(evt);
            }
        });
        btnrent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnrentKeyPressed(evt);
            }
        });

        dpcomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpcomonthActionPerformed(evt);
            }
        });

        btncancel3.setFont(new java.awt.Font("Arial", 0, 18));
        btncancel3.setText("Cancel");
        btncancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancel3ActionPerformed(evt);
            }
        });
        btncancel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncancel3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(71, 71, 71))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(dpcomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtstrike, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnrent, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btncancel3)
                                .addComponent(txtpremium, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtbrokerage, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstrike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpremium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpcomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbrokerage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancel3)
                    .addComponent(btnrent))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrentActionPerformed
        // TODO add your handling code here:
        rent();
    }//GEN-LAST:event_btnrentActionPerformed

    private void dpcomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpcomonthActionPerformed
        // TODO add your handling code here:
        if(!dpcomonth.getDate().toString().contains("Fri")) {
            JOptionPane.showMessageDialog(this,"Please Select Friday Only...");
            dpcomonth.setDate(null);
        }
    }//GEN-LAST:event_dpcomonthActionPerformed

    private void btnrentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnrentKeyPressed
        // TODO add your handling code here:
        rent();
    }//GEN-LAST:event_btnrentKeyPressed

    private void btncancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancel3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btncancel3ActionPerformed

    private void btncancel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncancel3KeyPressed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btncancel3KeyPressed
    public void rent()
    {
        /*Validator v=new Validator();
        v.add(txtpremium);
        v.add(txtstrike);
        v.add(txtbrokerage);

        if(v.isValidate() && dpcomonth.getDate()!=null){*/
            try
            {

                String copremium1=txtpremium.getText();

                Date comonth=dpcomonth.getDate();
                String costrike=txtstrike.getText();

                SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
                String date = sd.format(new Date());

                Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
                PreparedStatement pst = con.prepareStatement("insert into intervention values(?,?,?,?,?,?,?,?,?,?,?)");

                if(!(copremium1.equals("") || costrike.equals("") || comonth==null || txtbrokerage.getText().equals("")))
                {
                    pst.setString(1,date);
                    pst.setString(2,"New Position");
                    pst.setString(3,"Call");
                    pst.setString(4,"");
                    pst.setDouble(5,0.0);
                    pst.setDouble(6,0.0);
                    sd = new SimpleDateFormat("MMM dd ''yy");
                    pst.setString(7,sd.format(comonth));
                    pst.setDouble(8,Double.parseDouble(costrike));
                    pst.setDouble(9,Double.parseDouble(copremium1));
                    pst.setDouble(10,Double.parseDouble(txtbrokerage.getText()));
                    pst.setString(11,sc);

                    curr_break_even-=Double.parseDouble(copremium1);
                    Utility.updateBreakEven(curr_break_even,ssc);
                    Utility.updateCall(sd.format(comonth), Double.parseDouble(costrike), Double.parseDouble(copremium1),ssc);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Rent - Sell Call Option Successfully...");
                    this.dispose();
                }
                else
                {
                        String msg = "";
                     if(comonth==null)
                        msg+="\n->Sell Call Option Month";
                    if(costrike.equals(""))
                        msg+="\n->Sell Call Option Strike";
                    if(copremium1.equals(""))
                        msg+="\n->Sell Call Option Premium";
                    if(txtbrokerage.getText().equals(""))
                        msg+="\n->Brokerage";

                    JOptionPane.showMessageDialog(this,"Following Call Option Fields are empty:"+msg);
                }

                pst.close();
                con.close();
                
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this, e+"\nEnter Only numeric value...");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e);
            }
        /*}
        else
        {
                JOptionPane.showMessageDialog(this, "All fields are Compulsory!");
        }*/
    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel3;
    private javax.swing.JButton btnrent;
    private org.jdesktop.swingx.JXDatePicker dpcomonth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtbrokerage;
    private javax.swing.JTextField txtpremium;
    private javax.swing.JTextField txtstrike;
    // End of variables declaration//GEN-END:variables
    
}
