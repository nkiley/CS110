//The skeleton of the actual game
//Runs independently of the GUI system

import java.util.ArrayList;

public class Game
{
   Deck deck = new Deck();
   Hand playerHand = new Hand();
   Hand enemyHand = new Hand();
      
   CardPile playerField = new CardPile();
   CardPile enemyField = new CardPile();
   String roundWinner;
   int turn;
   boolean gameEnd = false;
   int warNumber = 1;
   int turnSegment = 0;
   int warSegment = 0;
   ArrayList<Hand> players = new ArrayList<>(2);
   
   
   public void begin()
   {
      //When the game is begun initialize the setup process
      players.add(playerHand);
      players.add(enemyHand);
      setUp();
   }
   
   public void setUp()
   {
      //Set everything to its starting conditions
      gameEnd = false;
      playerHand.clearCards();
      enemyHand.clearCards();
      deck.newDeck();
      turn = 0;
      turnSegment = 0;
      warSegment = 0;
      //Deal the deck to all players (only 2)
      distributeToPlayers(deck, players);
   }
   
   //Deal the deck to all players
   public void distributeToPlayers(Deck deck, ArrayList<Hand> players)
   {
      for(int i=0; i < (deck.DECK_SIZE / players.size()); i++)
      {
         for(int o=0; o < players.size(); o++)
         {
            if(deck.getSize() > 0)
               deck.dealTo(players.get(o));
         }
      }
   }
   
   //Run when the continue button is pressed
   //Broken up into segments
   
   //WarSegment:  Determines whether the game is in a war state or not, and at what point in the war state the game is in
   //TurnSegment:  Determines whether the game is dealing cards or giving away cards to the winner.  Broken up to decrease confusion for the player.
   public void nextTurn()
   {
      if(warSegment == 0)
      {
         //When turnSegment is 0, cards are played onto the field and compared
         if(turnSegment == 0)
         {
            turn +=1;
            playToField();
            analyzeField();
            turnSegment = 1;
         }
         //When turnSegment is 1, the cards on the field are given to the winner
         else if(turnSegment == 1)
         {
            rewardWinner();
            checkForWinner();
         }
      }
      else
      {
         //If warSegment is not 0, then the game is in a state of war.  war() handles at what point warSegment is in
         war();
      }
   }
   
   //Place cards from each players hand onto the field
   public void playToField()
   {
      playerHand.dealTo(playerField, true);
      enemyHand.dealTo(enemyField, true);
   }
   
   //This function determines the winner of the round and distributes cards accordingly.  It declares war if war is to be declared.
   public void analyzeField()
   {
      if(playerHand.getSize() < 0)
      {
         gameEnd = true;
         checkForWinner();
      }
      else if (enemyHand.getSize() < 0)
      {
         gameEnd = true;
         checkForWinner();
      }
      else
      {
         roundWinner = compareField();

         if(roundWinner.equals("War"))
         {
            warSegment = 1;
         }
      }
   }
   
   //Give the winner of the round the cards that were played during the round
   public void rewardWinner()
   {
      int pSize = playerField.getSize();
      int eSize = enemyField.getSize();
      if(roundWinner.equals("Player"))
      {
            for(int i = 0; i < pSize; i++)
               playerField.dealTo(playerHand, false);
            for(int i = 0; i < eSize; i++)
               enemyField.dealTo(playerHand, false);
      }
      else if(roundWinner.equals("Enemy"))
      {
         for(int i = 0; i < eSize; i++)
             enemyField.dealTo(enemyHand, false);
         for(int i = 0; i < pSize; i++)
            playerField.dealTo(enemyHand, false);
      }
      
      turnSegment = 0;
   }
   
   //This function returns the situation of the field, with three states: the player having the highest card, the enemy having the highest card, or war being declared.
   public String compareField()
   {
      if(playerField.getCard(playerField.getSize() - 1).isGreaterThan(enemyField.getCard(enemyField.getSize() - 1)))
      {
         return "Player";
      }
      else if(playerField.getCard(playerField.getSize() - 1).isLessThan(enemyField.getCard(enemyField.getSize() - 1)))
      {
         return "Enemy";
      }
      else
      {
         return "War";
      }
   }
   
   //This function handles war
   public void war()
   {
      //Each player plays a card facedown.  The game ends if a player does not have enough cards during this stage
      if(warSegment == 1)
      {
         if(playerHand.getSize() > 0)
            playerHand.dealTo(playerField, false);
         else
            checkForWinner();
            
         if(enemyHand.getSize() > 0)
            enemyHand.dealTo(enemyField, false);
         else
            checkForWinner();
         warSegment += 1;

      }
      //Each player plays a card faceup.  This determines the winner of the war
      else if(warSegment == 2)
      {
         if(playerHand.getSize() > 0)
            playerHand.dealTo(playerField, true);
         else
            checkForWinner();
               
         if(enemyHand.getSize() > 0)
            enemyHand.dealTo(enemyField, true);
         else
            checkForWinner();
            
            
         if(gameEnd == false)
            analyzeField();
         if(warSegment == 2)
         {
            warSegment = 0;
            turnSegment = 1;
         }
      }
   }
   
   //If a player has no cards, the game ends
   public void checkForWinner()
   {
      if(playerHand.getSize() < 1)
      {
         gameEnd = true;
      }
      if(enemyHand.getSize() < 1)
      {
         gameEnd = true;
      }
   }
}