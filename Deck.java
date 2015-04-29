//Deck class
//Is created then gives all its cards to each player equally in the game class
import java.util.Random;

public class Deck extends CardPile
{
   public static final int DECK_SIZE = 52;
   
   public Deck()
   {
      newDeck();
   }
   
   public void newDeck()
   {
      //Fill the deck with every single card
      clearCards();
      for(int r = 2; r <= Card.ACE; r++)
      {
         for(int s = 0; s <= Card.HEARTS; s++)
         {
            addToCards(new Card(s, r));
         }
      }
      
      shuffle();
   }
   
   //Shuffle the deck.
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < cardCount; i++)
      {
         randNum = r.nextInt(cardCount);
         temp = getCard(i);
         setCard(i, getCard(randNum));
         setCard(randNum, temp);
      }      
   }
}