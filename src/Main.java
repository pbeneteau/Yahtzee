
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.*;



public class Main implements YahtzeeConstants{

    private static Dices dices = new Dices();
    private static Utils utils = new Utils();
    private static Menu menu = new Menu();

    private static List<Player> players;

    public static void main(String[] args) {

        utils.showLogo(); // show game logo
        menu.showStartMenu(); // show start menu

        int choice = utils.inputInt(1,3); // get user menu choice

        switch (choice) {
            case 1: initGame(); break; // init game
            case 2: utils.showRules(); break; // show game rules
            case 3: return; // exit
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

            out.println("\n\n-------");
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

                    rollAction(player);
                }

                dices.rollDices(true);
            }

            round++;
        }
    }

    private static void rollAction(Player currentPlayer) {

        if (currentPlayer.getRoll() != 2) {

            menu.showRollActionMenu(currentPlayer, false);

            int choice = utils.inputInt(1, 5);

            switch (choice) {
                case 1: dices.holdDices(); break;
                case 2: dices.dropDices(); break;
                case 3: break;
                case 4: currentPlayer.getScore().setPatternPoints(dices.getDices());
                case 5: currentPlayer.show(); break;
            }

            if (choice == 5 || choice == 1 || choice == 2) {
                rollAction(currentPlayer);
            }
        } else {

            out.println("\nThis is the last roll ! \n");

            menu.showRollActionMenu(currentPlayer, true);

            int choice = utils.inputInt(1, 2);

            switch (choice) {
                case 1: currentPlayer.getScore().setPatternPoints(dices.getDices());
                case 2: currentPlayer.show(); break;
            }
        }
    }

    // input number of players who play
    static int getNumberOfPlayer() {

        menu.showNumberOfPlayerMenu(); // show the game mode selection menu (solo or multi-player)

        int choice = utils.inputInt(1,2); // get input from user (from 1 to 2)

        if (choice > 1) {
            out.print("\nEnter the number of player: ");
            choice = utils.inputInt(2, 10); // input number of players
        }

        return choice;
    }

    // Initialisation of players variable
    static List<Player> createPlayers(int number) {

        List<Player> players = new ArrayList<>(); // init array of users

        for (int i=0; i<number; i++) {
            players.add(new Player()); // add new empty Player variable
        }

        return players;
    }

    // Get users names input
    static void getPlayersName(List<Player> players) {

        out.println();

        for (Player player: players) {
            out.print("Player " + (player.getId()+1) + ", enter your name: ");
            String name = utils.inputString();
            if (name != "") {
                player.setName(name); // set name to Player object
            }
        }
    }

    // Check if the game must be ended or not (all player filled the tables)
    static boolean shouldEndGame() {

        return false;
    }


}

