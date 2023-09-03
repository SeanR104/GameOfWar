import java.util.ArrayList;
import java.util.Scanner;


public class Main{

	/**
	 * Name: Sean Ruda
	 * Date: 2/8/22
	 * Description: Simulates the game of war
	 * 
	 * Objective: 
	 * 	Play until one player runs out of cards. 
	 * 	Compare card values.  The bigger value wins both   cards. Aces are high.
	 *   	When there is a tie in the card value, decide based on suit.
	 * 
	 * Tie Game:
	 * 	Use suit ranking  
	 * 	Ascending alphabetical order: clubs (lowest), followed by diamonds, 
	 *	hearts, and spades (highest). 
	 *   	Winning a tie hand results in not only getting the card in a tie but also 
	 *	gaining two more of the opponents cards.
	 * 	FYI... This ranking is used in the game of bridge.
	 */


	public static void main(String[] args) {

    //Scanner variable
		Scanner myScan = new Scanner(System.in);

    //array lists for the pile the player1 pile and the player2 pile
		ArrayList<String> pile = new ArrayList<String>();
		ArrayList<String> player1 = new ArrayList<String>();
		ArrayList<String> player2 = new ArrayList<String>();
		int restart = 1;

		System.out.println("Welcome to War!");

    //while loop to keep looping the game
		while(restart == 1)
		{
			//calls the methods that run the game
      restartGame(pile, player1, player2);
			createPile(pile);
			deal(pile, player1, player2);
			shuffle(player1);
			shuffle(player2);

      //while loop to check if the 2 piles are greater than 0
			while(player1.size() > 0 && player2.size() > 0)
			{
        //sets value1 and value2 to the first element in each pile
				int value1 = getCardValue(player1.get(0));
				int value2 = getCardValue(player2.get(0));

        //prints the 2 cards at the top of the piles
				System.out.println(player1.get(0) + " vs. " + player2.get(0));

        //if else statements to determine who wins the hand
				if(value1 > value2)
				{
					//prints who won the hand and adds both cards to the bottem of the winners deck
          System.out.println("Player 1 wins that hand");
          player1.add(player2.remove(0));
          player1.add(player1.remove(0));
          System.out.println("----------------------");
				}
				else if(value1 < value2)
				{
					//prints who won the hand and adds both cards to the bottem of the winners deck
          System.out.println("Player 2 wins that hand");
          player2.add(player1.remove(0));
          player2.add(player2.remove(0));
          System.out.println("----------------------");
				}
				else // tie
				{
					//  Suit order from value to most value "Clubs" , "Diamonds" , "Hearts", "Spades"
					//gets suit values
          int player1suit = getSuitValue(player1.get(0));
					int player2suit = getSuitValue(player2.get(0));

          //if player 1 suit is greater than player 2 suit
					if(player1suit > player2suit)
					{					
						System.out.println("TIE except Player 1 wins that hand by suit order!");

            //sends both cards to the 1st players pile
            player1.add(player2.remove(0));
            player1.add(player1.remove(0));
            //if player 2's deck is bigger than 1 card
            if(player2.size() > 1) 
            {
              //sets the temp1 as the first card on the pile and adds it to the bottom
              String temp1 = player2.remove(0);
              player1.add(temp1);
              
              //sets the temp2 as the second card on the pile and adds it to the bottom
              String temp2 = player2.remove(0);
              player1.add(temp2);
              //prints out the two cards on the other pile
              System.out.println("Player 1 getting "  + temp1 + " and " + temp2);
              System.out.println("----------------------");
            }

            //if player 2's deck is 1 card
            else if(player2.size() == 1)
            {
              //sets temp1 as the first card and sends it to bottom then prints the card
              String temp1 = player2.remove(0);
              player1.add(temp1);
              System.out.println("Player 1 getting "  + temp1);
              System.out.println("----------------------");
            }
            //if there are no more cards left nothing happens
            else
            {
              
            }
					}
					//if player 2 suit is greater than player 1 suit
          else 
					{

						System.out.println("TIE except Player 2 wins that hand by suit order!");
            //sends both cards to the second players pile
            player2.add(player1.remove(0));
            player2.add(player2.remove(0));
            //if player 1's deck is greater than 1 card
            if(player1.size() > 1)
            {
              String temp1 = player1.remove(0);
              player2.add(temp1);
              

              String temp2 = player1.remove(0);
              player2.add(temp2);
              System.out.println("Player 2 getting "  + temp1 + " and " + temp2);
              System.out.println("----------------------");
            }
            //if player 1's deck is equal to 1 card
            else if(player1.size() == 1)
            {
              String temp1 = player1.remove(0);
              player2.add(temp1);
              System.out.println("Player 2 getting "  + temp1);
              System.out.println("----------------------");
            }
            //if no more cards in player 1's deck nothing happens
            else
            {
              
            }
					}
					//  Shuffle the cards for the players when there is a tie... it reduces the pattern for the same cards.
					//  Without occasional shuffling, I found myself in an infinite loop after a while.  It seemed like the
					//  cards had ordered themselves into big/small/big/small by the way I was placing the cards at the end of the deck.
					//  NOTE:  You CANNOT shuffle a deck that does not have any cards.  Your program will crash.
          if(player1.size() != 0)
          {
            shuffle(player1);
          }
					if(player2.size() != 0)
          {
            shuffle(player2);
          }
  

				}


			}
			//if else to determine the winner
      if(player1.size() == 0)
			{
				System.out.println("Player 2 won!");
        //System.out.println(player2.size());
        //System.out.println(player1.size());
        System.out.println("----------------------\n\n");
			}
			else
			{
				System.out.println("Player 1 won!");
        //System.out.println(player1.size());
        //System.out.println(player2.size());
        System.out.println("----------------------\n\n");
			}
			
			System.out.print("Would you like to play again?  Press 1 for yes, any other number to exit.");
			restart = myScan.nextInt();
			
		}
		System.out.println("Thanks for playing war!");

	}



  /*  Description: creates the initial deck
  *   Parameters: ArrayList<String> pile - the array list that is going to be filled
  *   Return type: void - nothing is being returned the list is just being filled  
  */
	public static void createPile(ArrayList<String> pile)
	{
		// This method creates the cards by looping through suit and card arrays.
		// The cards in the pile should read "A of Clubs" or "3 of Diamonds"
		// Hint:  Use a nested for loop. You will look very impressive if you can use
		// a nested enhanced for loop.  
		String[] suit = {"Clubs" , "Diamonds" , "Hearts", "Spades"};
		String[] card = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
		//fills the pile with the proper strings
    for(String value: card)
      {
        for(String type: suit)
          {
            pile.add(value + " of " + type);
          }
      }
	}

  /*  Description: deals the cards
  *   Parameters: ArrayList<String> pile, ArrayList<String> player1, ArrayList<String> player2 - includes the original pile and the 2 player piles to be dealt
  *   Return type: void - nothing is being returned the two player lists are being filled  
  */
	public static void deal(ArrayList<String> pile, ArrayList<String> player1, ArrayList<String> player2)
	{
		// randomly deal 26 cards to each player
		// after dealing, the original pile should be empty
		// Hint:  Print the pile and the two players lists to make sure the pile is empty and each
		// player has 26 cards.  Before submitting your assignment, comment out those lines.
    String player = "P1";
    //randomly deals the cards
    while(pile.size() != 0)
      {
        int randInt = (int)(Math.random() * pile.size());
        if(player.equals("P1"))
        {
          player1.add(pile.remove(randInt));
          player = "P2";
        }
        else
        {
          player2.add(pile.remove(randInt));
          player = "P1";
        }
      }
		// Do not use shuffle solely to deal randomly.  Shuffle could potentially only move a few cards and isn't a good
		// way to randomly deal the deck.  You are welcome to use shuffle here, but the card should be chosen
		// at random (for example don't give the first 26 to the first player and the remainder to the other or don't
		// give 0 to player1, 1 to player2, 2 to player1, etc....

	}

  /*  Description: gets the card values
  *   Parameters: String card - needs a card to determine its value
  *   Return type: int - returns the value of the card as an int
  */
	public static int getCardValue(String card)
	{
		// return the value of the card sent to this method
		// for example, T is 10, J is 11, Q is 12
		// Aces are high so they have a value of 14
		// Notice that a Ten is now "T of Hearts" and not "10 of Hearts" like in the Array lab
    //if else statements to determine the value
    if(card.substring(0, 1).equals("2"))
    {
      return 2;
    }
    else if(card.substring(0, 1).equals("3"))
    {
      return 3;
    }
    else if(card.substring(0, 1).equals("4"))
    {
      return 4;
    }
    else if(card.substring(0, 1).equals("5"))
    {
      return 5;
    }
    else if(card.substring(0, 1).equals("6"))
    {
      return 6;
    }
    else if(card.substring(0, 1).equals("7"))
    {
      return 7;
    }
    else if(card.substring(0, 1).equals("8"))
    {
      return 8;
    }
    else if(card.substring(0, 1).equals("9"))
    {
      return 9;
    }
    else if(card.substring(0, 1).equals("T"))
    {
      return 10;
    }
    else if(card.substring(0, 1).equals("J"))
    {
      return 11;
    }
    else if(card.substring(0, 1).equals("Q"))
    {
      return 12;
    }
    else if(card.substring(0, 1).equals("K"))
    {
      return 13;
    }
    
    return 14;
	}

  /*  Description: gets the suit values
  *   Parameters: String card - needs a card to determine its suit value
  *   Return type: int - returns the value of the suit of the card
  */
	public static int getSuitValue(String card)
	{
		// return a value of the suit in the card
		// hint:  use contains OR notice that C, D, H, S (the suit) is all in the same position of the card.
		// 5 of Diamonds, 3 of Clubs, etc....
    //if else statements to determine value based on suit
    if(card.contains("C"))
    {
      return 1;
    }
    else if(card.contains("D"))
    {
      return 2;
    }
    else if(card.contains("H"))
    {
      return 3;
    }
    
    return 4;
	}

  /*  Description: shuffles the cards
  *   Parameters: ArrayList<String> pile - needs a deck to shuffle
  *   Return type: void - nothing is returned the pile is shuffled
  */
	public static void shuffle(ArrayList<String> pile)
	{
		// This is already written for you.  DO NOT change this!
		int numberOfShuffles = (int)(Math.random() * pile.size());

		while(numberOfShuffles >= 0)
		{
			int card = (int)(Math.random() * pile.size());
			pile.add(pile.get(card));
			pile.remove(card);
			numberOfShuffles--;
		}

	}

  /*  Description: restarts the game
  *   Parameters: ArrayList<String> pile, ArrayList<String> player1, ArrayList<String> player2 - empties all the piles
  *   Return type: void - nothing is returned but the piles are emptied
  */
	public static void restartGame(ArrayList<String> pile, ArrayList<String> player1, ArrayList<String> player2)
	{
		// empty all array lists so when you deal again, all arrays do not have any remaining cards in them.
		// I would suggest printing the ArrayLists as a test to double check you have emptied all piles
		// Comment out the System.out.println before submitting your assignment
    for(int i = 0; i < player1.size(); i++)
      {
        player1.remove(i);
        i--;
      }
    for(int i = 0; i < player2.size(); i++)
      {
        player2.remove(i);
        i--;
      }
    for(int i = 0; i < pile.size(); i++)
      {
        player1.remove(i);
        i--;
      }
	
      //System.out.println(player1);
      //System.out.println(player2);
      //System.out.println(pile);
    
    }
}
