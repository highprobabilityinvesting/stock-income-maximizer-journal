/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package share_trading_journal.resources;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Alpesh
 */
public class Utility
{
        public static int cnt=0;

    public static void updateBreakEven(double cbe,String sc)
    {
         try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("update new_trade set Break_Even=? where Stock_Code=? and cnt=?");

           pst.setDouble(1,cbe);
           pst.setString(2, sc);
           pst.setInt(3, cnt);
           pst.executeUpdate();

           con.close();
        }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }
    public static void updateStockSoldPrice(double ssp,String sc)
    {
         try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("update new_trade set Stock_Sold_Price=? where Stock_Code=? and cnt=?");

           pst.setDouble(1,ssp);
           pst.setString(2, sc);
           pst.setInt(3, cnt);
           pst.executeUpdate();

           con.close();
        }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }
    public static void updateCall(String m,double s,double p,String sc)
    {
         try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("update new_trade set Expire_Month_CO=?,Strike_Price_CO=?,Premium_CO=? where Stock_Code=? and cnt=?");

           pst.setString(1, m);
           pst.setDouble(2,s);
           pst.setDouble(3,p);
           pst.setString(4, sc);
           pst.setInt(5, cnt);
           pst.executeUpdate();

           con.close();
        }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }
    public static void updatePut(String m,double s,double p,String sc)
    {
         try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("update new_trade set Expire_Month_PO=?,Strike_Price_PO=?,Premium_PO=? where Stock_Code=? and cnt=?");

           pst.setString(1, m);
           pst.setDouble(2,s);
           pst.setDouble(3,p);
           pst.setString(4, sc);
           pst.setInt(5, cnt);
           pst.executeUpdate();

           con.close();
        }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }
    public static void updateBrokerage(double b,String sc)
    {
         try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("update new_trade set Brokerage=Brokerage+? where Stock_Code=? and cnt=?");

           pst.setDouble(1,b);
           pst.setString(2, sc);
           pst.setInt(3, cnt);
           pst.executeUpdate();

           con.close();
        }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }
    public static void updateOutcome(String o,String s,String sc)
    {
         try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("update new_trade set Outcome=?,Status=? where Stock_Code=? and cnt=?");

           pst.setString(1,o);
           pst.setString(2,s);
           pst.setString(3, sc);
           pst.setInt(4, cnt);
           pst.executeUpdate();

           con.close();
        }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }

    public static void deleteTrade(String sc,int c)
    {
         try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("delete from new_trade where Stock_Code=? and cnt=?");
           pst.setString(1,sc);
           pst.setInt(2, cnt);
           pst.executeUpdate();
           sc+=c;
           pst = con.prepareStatement("delete from intervention where Stock_Code=?");
           pst.setString(1,sc+cnt);
           pst.executeUpdate();
           
           con.close();

           new File("charts/"+sc+cnt+".png").delete();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void updateComment(String comment,String ssc) 
    {
        try
         {
           Connection con = DriverManager.getConnection("jdbc:derby:database/share_trading");
           PreparedStatement pst = con.prepareStatement("update new_trade set Comment=? where Stock_Code=? and cnt=?");

           pst.setString(1,comment);
           pst.setString(2, ssc);
           pst.setInt(3, cnt);
           pst.executeUpdate();

           con.close();
           JOptionPane.showMessageDialog(null, "Comment Updated Successfully...");
        }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }
}
