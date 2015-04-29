//This class actually runs the game
//Activates the WarGameGUI sequence and creates the window.

import javax.swing.*;

public class WarGameGUIDriver
{
   public static void main(String [] args)
   {
      JFrame frame = new WarGameGUI();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
      // OR
      frame.setSize(1500,500);
      frame.validate();
      frame.setVisible(true);
   }
}