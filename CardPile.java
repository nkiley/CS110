//Import array list
import java.util.ArrayList;

//CardPile class

//This class stores an arraylist of Card classes
//Is used for player's hands, the field, and the deck
public class CardPile
{
   //Instance variables, 
   private ArrayList<Card> cards;
   public int cardCount;
   
   //Empty Constructor
   public CardPile()
   {
      cards = new ArrayList<Card>();
   }
   
   //Import a list of cards into CardPile
   public CardPile(ArrayList<Card> pile)
   {
      cards = new ArrayList<Card>();
      cards = pile;
      cardCount = cards.size();
   }
   
   //Copy constructor
   public CardPile(CardPile pile)
   {
      cards = new ArrayList<Card>();
      cards = pile.GetCards();
      cardCount = cards.size();
   }
   
   //Get the list of cards
   public ArrayList<Card> GetCards()
   {
      return cards;
   }
   
   //Add a new card to CardPile
   public void addToCards(Card card)
   {
      cards.add(card);
      cardCount += 1;
   }
   //Deal top card to another pile
   public void dealTo(CardPile pile)
   {
      pile.addToCards(removeTopCard());
   }
   
   public void dealTo(CardPile pile, boolean isFaceUp)
   {
      pile.addToCards(removeTopCard(isFaceUp));
   }
   
   //Remove the top card
   public Card removeTopCard()
   {
      Card topCard = new Card(cards.get(0));
      cards.remove(0);
      cardCount -= 1;
      return topCard;
   }
   public Card removeTopCard(boolean isFaceUp)
   {
      Card topCard = new Card(cards.get(0));
      cards.remove(0);
      cardCount -= 1;
      topCard.setIsFaceUp(isFaceUp);
      return topCard;
   }
   
   //Change a specific card
   public void setCard(int i, Card card)
   {
      cards.set(i, card);
   }
   
   //Get a specific card
   public Card getCard(int i)
   {
      return cards.get(i);
   }
   
   //Get size of card pile
   public int getSize()
   {
      return cards.size();
   }
   
   public boolean getTopCardIsFaceUp()
   {
      return cards.get(0).getIsFaceUp();
   }
   
   public Card getTopCard()
   {
      return cards.get(0);
   }
   
   //Remove all cards
   public void clearCards()
   {
      cards.clear();
      cardCount = 0;
   }
}