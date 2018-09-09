
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

        Player currentPlayer = players.get(getFirstPlayer()); // define random first player

        out.println(currentPlayer.getName() + " starts first !");

        while (!shouldEndGame()) {

            int rolls = 0;

            while (rolls < 3) { // player can rolls the dices only 3 times per round

                dices.rollDices(false); // roll the dices

                dices.show(); // show the dices that have been rolled

                rolls++; // increment rolls

                dices.holdDices();
            }

            int score = dices.checkPoints(currentPlayer);

            dices.rollDices(true);
        }
    }

    private static int selectCategory() {

        out.println("Select a category for this roll");

        int e = 0;

        utils.inputInt(1, 20);

        return e;
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

    // Generate random int that define the player who start first
    static int getFirstPlayer() {

        if(players.size() == 1)
        {
            return 0;
        }
        Random rand = new Random();
        return rand.nextInt(players.size() - 1);
    }

    // Check if the game must be ended or not (all player filled the tables)
    static boolean shouldEndGame() {

        return false;
    }

    static int GetTotalScore(int[][] upper_array, int[][] lower_array){
        int upper_section_total = 0;
        int lower_section_total= 0;

        for(int[] row : upper_array){
            upper_section_total += row[1];
        }

        if( upper_section_total >= 63){
            upper_section_total +=35;
        }

        for(int[] row : lower_array){
            lower_section_total+=row[1];
        }

        int total_score = upper_section_total+ lower_section_total;

        return total_score;
    }
}

