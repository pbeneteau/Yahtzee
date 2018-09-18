/* Marc-Antoine Bock && Paul Bénéteau Project Java Second Milestone */

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.*;


public class Main  {

    private static Dices dices = new Dices();
    private static Utils utils = new Utils();
    private static Menu menu = new Menu();

    private static List<Player> players;


    public static void main(String[] args) {

        utils.showLogo(); // show game logo
        menu.showStartMenu(); // show start menu

        int choice = utils.inputInt(1, 3); // get user menu choice

        switch (choice) {
            case 1:
                initGame();
                break; // init game
            case 2:
                utils.showRules();
                initGame();
                break; // show game rules
            case 3:
                out.println("\nThanks for playing Yahtzee! Goodbye.");
                return; // exit
        }

    }

    // initiate all starting/useful data to start the game
    private static void initGame() {

        int playersNumber = getNumberOfPlayer(); // ask for number of players

        players = createPlayers(playersNumber); // create players variables

        getPlayersName(players); // ask player to input their name

        play(); // launch the game
    }

    private static void play() {

        int round = 0;

        while (!shouldEndGame()) {

            out.println("\n-------");
            out.println("Round " + round);
            out.println("-------\n");

            for (Player player : players) {

                int rolls = 0;
                int choice = 0;

                player.setRound(round);

                while (rolls < 3) { // player can rolls the dices only 3 times per round

                    player.setRoll(rolls);

                    dices.rollDices(false); // roll the dices

                    dices.show(); // show the dices that have been rolled

                    rolls++; // increment rolls

                    choice = rollAction(player);

                    if (choice == 4) { break; }
                }

                dices.rollDices(true);
            }

            round++;
        }

        Player winner = getWinner();

        out.println(winner.getName() + "won the game with "+ winner.getScore().getTotalPoints() + " !");
    }

    private static int rollAction(Player currentPlayer) {

        if (currentPlayer.getRoll() != 2) {

            menu.showRollActionMenu(currentPlayer, false);

            int choice = utils.inputInt(1, 5);

            switch (choice) { // handle user choice
                case 1:
                    dices.holdDices();
                    break;
                case 2:
                    dices.dropDices();
                    break;
                case 3:
                    break;
                case 4:
                    currentPlayer.getScore().setPatternPoints(dices.getDices());
                case 5:
                    currentPlayer.show();
                    break;
            }

            if (choice == 5 || choice == 1 || choice == 2) {
                return rollAction(currentPlayer);
            }
            return choice;
        } else {

            out.println("\nThis is the last roll ! \n");

            menu.showRollActionMenu(currentPlayer, true);

            int choice = utils.inputInt(1, 2);

            switch (choice) {
                case 1:
                    currentPlayer.getScore().setPatternPoints(dices.getDices());
                case 2:
                    currentPlayer.show();
                    break;
            }
            return choice;
        }
    }

    // input number of players who play
    static int getNumberOfPlayer() {

        menu.showNumberOfPlayerMenu(); // show the game mode selection menu (solo or multi-player)

        int choice = utils.inputInt(1, 2); // get input from user (from 1 to 2)

        if (choice > 1) {
            out.print("\nEnter the number of player (10 max): ");
            choice = utils.inputInt(2, 10); // input number of players
        }

        return choice;
    }

    // Initialisation of players variable
    static List<Player> createPlayers(int number) {

        List<Player> players = new ArrayList<>(); // init array of users

        for (int i = 0; i < number; i++) {

            players.add(new Player()); // add new empty Player variable
        }

        return players;
    }

    // Get users names input
    static void getPlayersName(List<Player> players) {

        out.println();

        for (Player player : players) {

            out.print("Player " + (player.getId() + 1) + ", enter your name: ");
            String name = utils.inputString();
            if (name != "") {
                player.setName(name); // set name to Player object
            }
        }
    }

    // Check if the game must be ended or not (all player filled the tables)
    static boolean shouldEndGame() {

        for (Player player : players) {

            for (int i = 0; i < player.getScore().getUpperSection().length; i++) {

                if (player.getScore().getUpperSection()[i][2] != 1) { // if score in upper array are not all add
                    return false;
                }
            }
            for (int i = 0; i < player.getScore().getLowerSection().length; i++) { // and score in lower array are not all add
                if (player.getScore().getLowerSection()[i][2] != 1) {
                    return false;
                }
            }

        }
        return true;
    }

    // Return the winner of the game (most points)
   private static Player getWinner() {

        int totalPoints = 0;
        Player winner = new Player();

        for (Player player: players) {

            if (player.getScore().getTotalPoints() > totalPoints) {

                totalPoints = player.getScore().getTotalPoints();
                winner = player;
            }
        }

        return winner;
    }
}


/*
Ideas and enhancement

Playing on a log console is very tricky. To enhance the game we could create a graphic interface to improve the usability of the game.
We also could review the code to make it more clear and improve the algorithm efficiency.


Moreover, some features could be added to the game like a points prediction: points would be automatically calculated before the user select his pattern. That would prevent the user next score to ensure that he makes the right choice. In addition, we could create an IA that plays against the user, with different levels of difficulty.
 */
