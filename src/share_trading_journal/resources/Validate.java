/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package share_trading_journal.resources;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class Validate
{
    public static boolean isNumeric(String str)
    {
         try
         {
             Double.parseDouble(str);
         }
         catch(NumberFormatException e)
         {
             return false;
         }
         return true;
    }
}
class Validator extends ArrayList
{
    public boolean isValidate()
    {
        for(int i=0;i<size();i++)
        {
            if(((JTextField)get(i)).getText().equals(""))
            {
                ((JTextField)get(i)).requestFocus();
                return false;
            }
        }
        return true;
    }
}
