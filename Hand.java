import java.util.Random;

//The player and the enemy's hand.

//Was made capable of shuffling in case of unneeded special conditions
public class Hand extends CardPile
{
   
   public Hand()
   {
      super();
   }
   
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