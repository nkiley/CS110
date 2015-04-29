//GUI system
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WarGameGUI extends JFrame
{
   //Create necessary objects
   private Game game;
   private JPanel displayPanel, titlePanel, bottomBufferPanel, gamePanelLeft, gamePanelMiddle, gamePanelRight;
   private JLabel title, status, playerHandLabel, enemyHandLabel;
   private JButton advance, reset;
   private JLabel yourPile, yourField, enemyPile, enemyField;
   private JLabel [] cards;
   
   public WarGameGUI()
   {
      //Set the visual implementation of the game
      setLayout(new GridLayout(1,1));
      game = new Game();
      
      displayPanel = new JPanel(new BorderLayout());
      displayPanel.setBackground(Color.yellow);
      
      titlePanel = new JPanel();
      titlePanel.setOpaque(false);
      title = new JLabel("Nick Kiley's Game of War");
      title.setFont(new Font("ARIAL", Font.ITALIC, 36));
      titlePanel.add(title, BorderLayout.CENTER);
            
      //Left Game Panel contains the player's deck
      gamePanelLeft = new JPanel(new BorderLayout());
      gamePanelLeft.setOpaque(false);
      yourPile = new JLabel("");
      yourPile.setIcon(new ImageIcon("cardpics/back.jpg"));
      playerHandLabel = new JLabel("Player's Deck");
      playerHandLabel.setFont(new Font("ARIAL",Font.BOLD, 18));
      gamePanelLeft.add(yourPile, BorderLayout.WEST);
      gamePanelLeft.add(playerHandLabel, BorderLayout.SOUTH);
      
      //Middle Game Panel contains the active cards and the continue button
      gamePanelMiddle = new JPanel(new BorderLayout());
      JPanel blankSpaceCenter = new JPanel(new BorderLayout());
      blankSpaceCenter.setOpaque(false);
      gamePanelMiddle.setOpaque(false);
      yourField = new JLabel("");
      enemyField = new JLabel("");
      status = new JLabel("Let's begin!");
      status.setFont(new Font("ARIAL", Font.BOLD, 24));
      status.setHorizontalAlignment(JLabel.CENTER);
      advance = new WGButton("Continue", "Continue");
      advance.setFont(new Font("ARIAL", Font.BOLD, 48));
      advance.addActionListener(new ButtonListener());
      reset = new WGButton("Reset", "Reset");
      reset.setFont(new Font("ARIAL", Font.BOLD, 28));
      reset.addActionListener(new ButtonListener());
      yourField.setIcon(new ImageIcon("cardpics/empty.png"));
      enemyField.setIcon(new ImageIcon("cardpics/empty.png"));
      blankSpaceCenter.add(advance, BorderLayout.CENTER);
      blankSpaceCenter.add(reset, BorderLayout.SOUTH);
      gamePanelMiddle.add(status, BorderLayout.SOUTH);
      gamePanelMiddle.add(yourField, BorderLayout.WEST);
      gamePanelMiddle.add(enemyField, BorderLayout.EAST);
      gamePanelMiddle.add(blankSpaceCenter, BorderLayout.CENTER);
      
      //Right Game Panel contains the enemy's deck
      gamePanelRight = new JPanel(new BorderLayout());
      gamePanelRight.setOpaque(false);
      enemyPile = new JLabel("");
      enemyPile.setIcon(new ImageIcon("cardpics/back.jpg"));
      enemyHandLabel = new JLabel("Enemy's Deck");
      enemyHandLabel.setFont(new Font("ARIAL",Font.BOLD, 18));
      gamePanelRight.add(enemyPile, BorderLayout.EAST);
      gamePanelRight.add(enemyHandLabel, BorderLayout.SOUTH);
      
      bottomBufferPanel = new JPanel();
      bottomBufferPanel.setOpaque(false);
      
      displayPanel.add(titlePanel, BorderLayout.NORTH);
      displayPanel.add(bottomBufferPanel, BorderLayout.SOUTH);
      displayPanel.add(gamePanelLeft, BorderLayout.WEST);
      displayPanel.add(gamePanelMiddle, BorderLayout.CENTER);
      displayPanel.add(gamePanelRight, BorderLayout.EAST);
      add(displayPanel);
      
      //Start the actual game
      game.begin();
   }
   
   public void updateGUI()
   {
      if(game.gameEnd == false)
      {
         //Update player field
         if(game.playerField.getSize() > 0)
            yourField.setIcon(new ImageIcon(game.playerField.getCard(game.playerField.getSize() - 1).getImageName()));
         else
            yourField.setIcon(new ImageIcon("cardpics/empty.png"));
         
         //Update enemy field
         if(game.enemyField.getSize() > 0)
            enemyField.setIcon(new ImageIcon(game.enemyField.getCard(game.enemyField.getSize() - 1).getImageName()));
         else
            enemyField.setIcon(new ImageIcon("cardpics/empty.png"));
            
         //Update player deck
         if(game.playerHand.getSize() > 0)
            yourPile.setIcon(new ImageIcon("cardpics/back.jpg"));
         else
            yourPile.setIcon(new ImageIcon("cardpics/empty.png"));
         playerHandLabel.setText("Player's Deck: "+game.playerHand.getSize()+ " cards");
            
         //Update enemy deck
         if(game.enemyHand.getSize() > 0)
            enemyPile.setIcon(new ImageIcon("cardpics/back.jpg"));
         else
            enemyPile.setIcon(new ImageIcon("cardpics/empty.jpg"));
         enemyHandLabel.setText("Enemy's Deck: "+game.enemyHand.getSize()+ " cards");  
         
         //Update the status label
            if(game.warSegment > 0)
            {
               status.setText("We are at WAR!");
               if(game.warSegment == 1)
                  advance.setText("Place your facedown card");
               if(game.warSegment == 2)
                  advance.setText("Play your WAR card!");
            }
            else if (game.turnSegment == 1)
            {
               if(game.roundWinner.equals("Player"))
               {
                  status.setText("End of turn "+game.turn+".  The player wins this round!");
                  advance.setText("Collect your "+(game.playerField.getSize() + game.enemyField.getSize()) + " card prize!");
               }
               else
               {
                  status.setText("End of turn "+game.turn+".  The enemy wins this round!");
                  advance.setText("Surrender The "+(game.playerField.getSize() + game.enemyField.getSize()) + " card prize...");
               }
         }
         else
         {
            status.setText("Distributing cards to the winner.");
            advance.setText("Next Round");
         }
      }
      else
      {
         advance.setText("Game Over!");
         advance.setEnabled(false);
         if(game.playerHand.getSize() > 0)
            status.setText("The player wins!  That took "+game.turn+" turns!");
         else
            status.setText("The player loses!  That took "+game.turn+" turns!");
      }
         
   }
   
   //Activate when a button is pressed
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         WGButton source = (WGButton)(e.getSource());
         //Run when continue button is pressed
         if(source.getIdentity().equals("Continue"))
         {
            game.nextTurn();
            updateGUI();
         }
         else //Otherwise the only other button is the reset button
         {
            System.out.println("Let's reset!");
            game.setUp();
            updateGUI();
            advance.setText("Continue");
            status.setText("Let's begin!");
            playerHandLabel.setText("Player's Deck");
            enemyHandLabel.setText("Enemy's Deck");
            yourField.setIcon(new ImageIcon("cardpics/empty.png"));
            enemyField.setIcon(new ImageIcon("cardpics/empty.png"));
            advance.setEnabled(true);
         }
      }
   }
}