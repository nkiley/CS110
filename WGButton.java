import javax.swing.*;
// Represents a button with a specific identity.  The identity tells the actionlistener which button was pressed.
//Identifying a button by text would not work as the continue button's text constantly changes
public class WGButton extends JButton
{  
   private String identity;
   // constructor
   public WGButton(String identity, String s)
   {
      super(s);
      this.identity = identity;
   }
   
   public String getIdentity()
   {
      return identity;
   }
}