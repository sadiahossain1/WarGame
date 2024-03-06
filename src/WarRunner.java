import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class WarRunner{

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"♠", "♥", "♦", "♣"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};


    public static void main(String[] args)
    {
        //beginningDeck is the Deck we start with.  When we deal, it gets split into two
        //Decks for each player
        Deck beginningDeck = new Deck(RANKS,SUITS,POINT_VALUES);
        beginningDeck.shuffle();
        //System.out.println(beginningDeck);

        Deck computerDeck = new Deck();
        Deck playerDeck = new Deck();

        while (!beginningDeck.isEmpty()) {
            computerDeck.addToTop(beginningDeck.deal());
            playerDeck.addToTop(beginningDeck.deal());
        }

        System.out.println("Players have been dealt their cards!");
        String quit = "n";
        Scanner scan = new Scanner(System.in);
        int plays = 0;
        Deck playedDeck = new Deck();
        while (!quit.equals("y")) {
            Card compCard = computerDeck.deal();
            System.out.println("Computer has played a " + compCard);
            playedDeck.addToTop(compCard);
            System.out.print("Press enter to play: ");
            scan.nextLine();
            Card playerCard = playerDeck.deal();
            System.out.println("You played a " + playerCard);
            playedDeck.addToTop(playerCard);
            plays++;
            if (compCard.pointValue() > playerCard.pointValue()) {
                System.out.println("Computer wins! " + compCard + " beats " + playerCard + "\n");
                while (!playedDeck.isEmpty()) {
                    computerDeck.addToBottom(playedDeck.deal());
                System.out.println("Computer has " + computerDeck.size() + " and you have " + playedDeck.size());
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    playedDeck.addToBottom(computerDeck.deal());
                    playedDeck.addToBottom(playerDeck.deal());
                }
                System.out.println("TIE! Each player has laid down 3 cards");
            }
            if (plays % 26 == 0) {
                System.out.print("You've played " + plays + "\nReady to quit? (y/n): ");
                quit = scan.nextLine();
            }
        }
        System.out.println("Game over!");
        if (playerDeck.size() > computerDeck.size()) {
            System.out.println("Player won with " + playerDeck.size() + " and computer had " + computerDeck.size());
        } else if (playerDeck.size() < computerDeck.size()) {
            System.out.println("Computer won with " + computerDeck.size() + " and player had " + playedDeck.size());
        } else {
            System.out.println("A Tie! 26 to 26!");

        }
    }
}