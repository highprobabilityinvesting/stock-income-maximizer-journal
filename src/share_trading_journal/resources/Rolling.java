/*
 * Roling.java
 *
 * Created on June 3, 2014, 11:18 PM
 */

package share_trading_journal.resources;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 *
 * @author  Ravi Patel
 */
public class Rolling extends javax.swing.JFrame {
    
    /** Creates new form Rolling */
    double curr_break_even=0.0;
    String sc="",ssc="";
     double strike1=0.0,strike2=0.0;
     boolean tracall=false,traput=false;
    public Rolling(String sc,String ssc) {
        initComponents();
        this.sc=sc;
        this.ssc = ssc;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         dpcomonth.setFormats("MMM dd ''yy");
         dppomonth.setFormats("MMM dd ''yy");
        try
        {
            Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Expire_Month_CO,Strike_Price_CO,Expire_Month_PO,Strike_Price_PO,Break_Even from new_trade where Stock_Code='"+ssc+"' and cnt="+Utility.cnt);

            while(rs.next())
            {
                 lblcomonth.setText(rs.getString("Expire_Month_CO"));
                 strike1=rs.getDouble("Strike_Price_CO");
                 lblcostrike.setText("$"+String.format("%,.2f",strike1));

                 lblpomonth.setText(rs.getString("Expire_Month_PO"));
                 strike2=rs.getDouble("Strike_Price_PO");
                 lblpostrike.setText("$"+String.format("%,.2f",strike2));

                 curr_break_even = rs.getDouble("Break_Even");
            }
            
            st.close();
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblcomonth = new javax.swing.JLabel();
        lblcostrike = new javax.swing.JLabel();
        txtcopremium1 = new javax.swing.JTextField();
        txtcostrike = new javax.swing.JTextField();
        txtcopremium2 = new javax.swing.JTextField();
        dpcomonth = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblpomonth = new javax.swing.JLabel();
        lblpostrike = new javax.swing.JLabel();
        txtpopremium1 = new javax.swing.JTextField();
        txtpostrike = new javax.swing.JTextField();
        txtpopremium2 = new javax.swing.JTextField();
        dppomonth = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtbrokerage = new javax.swing.JTextField();
        btnroll = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rolling");
        setIconImage(new ImageIcon("images/Sharelord_logo.png").getImage());
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("Rolling");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel4.setText("Month");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel5.setText("Strike");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel6.setText("Premium");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel10.setText("Buy To Close");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel11.setText("Sell To Open");

        lblcomonth.setFont(new java.awt.Font("Arial", 0, 14));

        lblcostrike.setFont(new java.awt.Font("Arial", 0, 14));

        dpcomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpcomonthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcomonth)
                    .addComponent(jLabel4)
                    .addComponent(dpcomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lblcostrike)
                    .addComponent(txtcostrike, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcopremium2, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(txtcopremium1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcomonth)
                    .addComponent(lblcostrike)
                    .addComponent(txtcopremium1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtcostrike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcopremium2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpcomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel7.setText("Month");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel8.setText("Strike");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Premium");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel12.setText("Sell To Close");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel13.setText("Buy To Open");

        lblpomonth.setFont(new java.awt.Font("Arial", 0, 14));

        lblpostrike.setFont(new java.awt.Font("Arial", 0, 14));

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblpomonth)
                            .addComponent(jLabel7))
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dppomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lblpostrike)
                    .addComponent(txtpostrike, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtpopremium2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtpopremium1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblpomonth)
                    .addComponent(lblpostrike)
                    .addComponent(txtpopremium1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtpostrike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpopremium2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dppomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel2.setText("Call Option");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel3.setText("Put Option");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel14.setText("Brokerage");

        btnroll.setFont(new java.awt.Font("Arial", 0, 18));
        btnroll.setText("Roll");
        btnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrollActionPerformed(evt);
            }
        });
        btnroll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnrollKeyPressed(evt);
            }
        });

        btnclose.setFont(new java.awt.Font("Arial", 0, 18));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel14)
                        .addGap(26, 26, 26)
                        .addComponent(txtbrokerage, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtbrokerage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnclose, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btnroll))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void rolling()
    {
        boolean flag=false;
        try
        {
            String copremium1=txtcopremium1.getText();
            String copremium2=txtcopremium2.getText();
            Date comonth=dpcomonth.getDate();
            String costrike=txtcostrike.getText();

            String popremium1=txtpopremium1.getText();
            String popremium2=txtpopremium2.getText();
            Date pomonth=dppomonth.getDate();
            String postrike=txtpostrike.getText();

            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            String date = sd.format(new Date());

            Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
            PreparedStatement pst = con.prepareStatement("insert into intervention values(?,?,?,?,?,?,?,?,?,?,?)");
            /*if(txtbrokerage.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please enter brokerage...");
                txtbrokerage.requestFocus();
                return;
            }*/
            if(!(copremium1.equals("") || copremium2.equals("") || costrike.equals("") || comonth==null || txtbrokerage.getText().equals("")))
            {
                pst.setString(1,date);
                pst.setString(2,"Roll");
                pst.setString(3,"Call");
                pst.setString(4,lblcomonth.getText());
                pst.setDouble(5,strike1);
                pst.setDouble(6,Double.parseDouble(copremium1));
                sd = new SimpleDateFormat("MMM dd ''yy");
                pst.setString(7,sd.format(comonth));
                pst.setDouble(8,Double.parseDouble(costrike));
                pst.setDouble(9,Double.parseDouble(copremium2));
                pst.setDouble(10,Double.parseDouble(txtbrokerage.getText()));
                pst.setString(11,sc);

                if((popremium1.equals("") || popremium2.equals("") || postrike.equals("") || pomonth==null))
                {
                     curr_break_even+=Double.parseDouble(copremium1)-Double.parseDouble(copremium2);
                     Utility.updateBreakEven(curr_break_even,ssc);
                     Utility.updateCall(sd.format(comonth), Double.parseDouble(costrike), Double.parseDouble(copremium2),ssc);
                }
                else
                {
                    flag=true;
                }
                pst.executeUpdate();
                tracall=true;
                JOptionPane.showMessageDialog(this,"Rolling Call Option Successfully...");

            }
            else
            {
                if(!(copremium1.equals("") && copremium2.equals("") && costrike.equals("") && comonth==null))
                {
                    if(txtbrokerage.getText().equals(""))
                    {
                        String msg = "";
                        if(copremium1.equals(""))
                            msg+="\n->Buy to Close Call Premium";
                        if(copremium2.equals(""))
                            msg+="\n->Sell to Open Call Premium";
                        if(costrike.equals(""))
                            msg+="\n->Sell to Open Call Strike";
                        if(comonth==null)
                            msg+="\n->Sell to Open Call Month";
                        if(txtbrokerage.getText().equals(""))
                            msg+="\n->Brokerage";

                        JOptionPane.showMessageDialog(this,"Following Call Option Fields are empty:"+msg);
                    }
                }

            }
            if(!(popremium1.equals("") || popremium2.equals("") || postrike.equals("") || pomonth==null || txtbrokerage.getText().equals("")))
            {
                pst.setString(1,date);
                pst.setString(2,"Roll");
                pst.setString(3,"Put");
                pst.setString(4,lblpomonth.getText());
                pst.setDouble(5,strike2);
                pst.setDouble(6,Double.parseDouble(popremium1));
                sd = new SimpleDateFormat("MMM dd ''yy");
                pst.setString(7,sd.format(pomonth));
                pst.setDouble(8,Double.parseDouble(postrike));
                pst.setDouble(9,Double.parseDouble(popremium2));
                pst.setDouble(10,Double.parseDouble(txtbrokerage.getText()));
                pst.setString(11,sc);

                if((copremium1.equals("") || copremium2.equals("") || costrike.equals("") || comonth==null))
                {
                    curr_break_even-=Double.parseDouble(popremium1)+Double.parseDouble(popremium2);
                    Utility.updateBreakEven(curr_break_even,ssc);
                    Utility.updateCall(sd.format(pomonth), Double.parseDouble(postrike), Double.parseDouble(popremium2),ssc);
                }
                else
                {
                    flag=true;
                }

                pst.executeUpdate();
                traput=true;
                JOptionPane.showMessageDialog(this,"Rolling Put Option Successfully...");
            }
            else
            {
                if(!(popremium1.equals("") && popremium2.equals("") && postrike.equals("") && pomonth==null))
                {
                    if(txtbrokerage.getText().equals(""))
                    {
                        String msg = "";
                        if(popremium1.equals(""))
                            msg+="\n->Sell to Close Put Premium";
                        if(popremium2.equals(""))
                            msg+="\n->Buy to Open Put Premium";
                        if(postrike.equals(""))
                            msg+="\n->Buy to Open Put Strike";
                        if(pomonth==null)
                            msg+="\n->Buy to Open Put Month";
                        if(txtbrokerage.getText().equals(""))
                            msg+="\n->Brokerage";

                        JOptionPane.showMessageDialog(this,"Following Put Option Fields are empty:"+msg);
                    }
                }
            }

            if(flag)
            {
                 curr_break_even+=Double.parseDouble(copremium1)-Double.parseDouble(copremium2)-Double.parseDouble(popremium1)+Double.parseDouble(popremium2);
                 Utility.updateBreakEven(curr_break_even,ssc);
                 Utility.updateCall(sd.format(comonth), Double.parseDouble(costrike), Double.parseDouble(copremium2),ssc);
                 Utility.updatePut(sd.format(pomonth), Double.parseDouble(postrike), Double.parseDouble(popremium2),ssc);
                 this.dispose();
            }

            pst.close();
            
            /*if(tracall)
            {
                Statement st = con.createStatement();
                st.executeUpdate("delete from intervention where Action='New  Position' and Opt_Type='Call'");
                st.close();
            }
            if(traput)
            {
                Statement st = con.createStatement();
                st.executeUpdate("delete from intervention where Action='New  Position' and Opt_Type='Put'");
                st.close();
            }*/
            
            con.close();
            if(tracall || traput)
                this.dispose();
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, e+"\nEnter Only numeric value...");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }
    private void btnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrollActionPerformed
        // TODO add your handling code here:
        rolling();
        
    }//GEN-LAST:event_btnrollActionPerformed

    private void dpcomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpcomonthActionPerformed
        // TODO add your handling code here:
        if(!dpcomonth.getDate().toString().contains("Fri")) {
            JOptionPane.showMessageDialog(this,"Please Select Friday Only...");
            dpcomonth.setDate(null);
        }
    }//GEN-LAST:event_dpcomonthActionPerformed

    private void dppomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dppomonthActionPerformed
        // TODO add your handling code here:
        if(!dppomonth.getDate().toString().contains("Fri")) {
            JOptionPane.showMessageDialog(this,"Please Select Friday Only...");
            dppomonth.setDate(null);
        }
    }//GEN-LAST:event_dppomonthActionPerformed

    private void btnrollKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnrollKeyPressed
        // TODO add your handling code here:
        rolling();
    }//GEN-LAST:event_btnrollKeyPressed

    private void btncloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btncloseActionPerformed

    private void btncloseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncloseKeyPressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btncloseKeyPressed
    
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btnroll;
    private org.jdesktop.swingx.JXDatePicker dpcomonth;
    private org.jdesktop.swingx.JXDatePicker dppomonth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel lblcomonth;
    private javax.swing.JLabel lblcostrike;
    private javax.swing.JLabel lblpomonth;
    private javax.swing.JLabel lblpostrike;
    private javax.swing.JTextField txtbrokerage;
    private javax.swing.JTextField txtcopremium1;
    private javax.swing.JTextField txtcopremium2;
    private javax.swing.JTextField txtcostrike;
    private javax.swing.JTextField txtpopremium1;
    private javax.swing.JTextField txtpopremium2;
    private javax.swing.JTextField txtpostrike;
    // End of variables declaration//GEN-END:variables
    
}
