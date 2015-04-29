//Import random
import java.util.Random;

//Card class
//Stores a suit, a rank, and the state of being faceup or facedown.  Is capable of comparing ranks with other cards. 
public class Card
{  //Instance variables
   private int suit;
   private int rank;
   private boolean isFaceUp;
   //Static variables
   private static final int MAX_SUIT = 3;
   private static final int MIN_SUIT = 0;
   private static final int MAX_RANK = 14;
   private static final int MIN_RANK = 2;
   public static final int SPADES = 0;
   public static final int CLUBS = 1;
   public static final int DIAMONDS = 2;
   public static final int HEARTS = 3;
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;
   public static final int ACE = 14;
   public static final String directory = "cardpics/";
   private static final Random rand = new Random();
   private static final String [] suitNames = new String[] {"Spades", "Clubs", "Diamonds", "Hearts"};
   private static final String [] rankNames = new String[] {"one","two","three","four","five","six","seven","eight","nine","ten","Jack","Queen","King","Ace"};
   private static final String [] suitImageNames = new String[] {"s","c","d","h"};
   private static final String [] rankImageNames = new String[] {"1","2","3","4","5","6","7","8","9","10","jack","queen","king","ace"};
   
   //Empty constructor - creates random facedown card
   public Card()
   {
      this(rand.nextInt(MAX_SUIT + 1) + MIN_SUIT, rand.nextInt(MAX_RANK + 1) + MIN_RANK);
   }
   //Three operation constructor, creates card with specified rank, suit, and position
   public Card(int suit, int rank, boolean isFaceUp)
   {
      if (suit > MAX_SUIT)
      {
         this.suit = MAX_SUIT;
      }
      else if (suit < MIN_SUIT)
      {
         this.suit = MIN_SUIT;
      }
      else
      {
         this.suit = suit;
      }
      
      if (rank > MAX_RANK)
      {
         this.rank = MAX_RANK;
      }
      else if (rank < MIN_RANK)
      {
         this.rank = MIN_RANK;
      }
      else
      {
         this.rank = rank;
      }
      
      this.isFaceUp = isFaceUp;
   }
   
   //Two operation constructor, creates facedown card with specified rank and suit
   public Card(int suit, int rank)
   {
      this(suit, rank, false);
   }
   
   //Copy constructor
   public Card(Card other)
   {
      suit = other.getSuit();
      rank = other.getRank();
      isFaceUp = other.getIsFaceUp();
   }
   
   //Getters
   //Method to acquire rank of the card
   public int getRank()
   {
      return rank;
   }
   
   //Method to acquire suit of the card
   public int getSuit()
   {
      return suit;
   }
   
   //Method to acquire position value of the card
   public boolean getIsFaceUp()
   {
      return isFaceUp;
   }
   
   public String getImageName()
   {
      if(isFaceUp)
         return ""+directory+rankImageNames[rank -1 ]+suitImageNames[suit]+".jpg";
      else
         return "cardpics/back.jpg";
   }
   
   //Setters
   //Method to set rank of the card
   public void setRank(int value)
   {
      if (value > MAX_RANK)
      {
         rank = MAX_RANK;
      }
      else if (rank < MIN_RANK)
      {
         rank = MIN_RANK;
      }
      else
      {
         rank = value;
      }
   }
   
   //Method to set suit of the card
   public void setSuit(int value)
   {
      if (suit > MAX_SUIT)
      {
         suit = MAX_SUIT;
      }
      else if (suit < MIN_SUIT)
      {
         suit = MIN_SUIT;
      }
      else
      {
         suit = value;
      }
   }
   
   //Method to set position value of the card
   public void setIsFaceUp(boolean value)
   {
      isFaceUp = value;
   }
   
   //Method to compare if rank is equal to another card's rank
   public boolean isEqualTo(Card other)
   {
      if(rank == other.getRank())
         return true;
      return false;
   }
   
   //Method to compare if rank is greater than another card's rank
   public boolean isGreaterThan(Card other)
   {
      if(rank > other.getRank())
         return true;
      return false;
   }
   
   //Method to compare if rank is less than another card's rank
   public boolean isLessThan(Card other)
   {
      if(rank < other.getRank())
         return true;
      return false;
   }
   
   //Convert card values to string if face up
   public String toString()
   {
      if(isFaceUp)
         return "the "+rankNames[rank - 1]+" of "+suitNames[suit];
      else
         return "unknown";
   }
}