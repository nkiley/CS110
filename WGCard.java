import javax.swing.*;
// Represents a button specialized for TICTACTOE
// Each button know's it r,c coordinates
public class WGCard extends JLabel
{  
   String owner;
   // constructor
   public WGCard(String owner, String s)
   {
      super(s);
      this.owner = owner;
   }
   
   public String getOwner()
   {
      return owner;
   }
}