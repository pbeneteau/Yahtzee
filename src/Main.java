
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.in;
import static java.lang.System.out;



public class Main {

    static Dices dices = new Dices();
    static Utils utils = new Utils();
    static Menu menu = new Menu();

    private static List<Player> players;

    public static void main(String[] args) {

        utils.showLogo();
        menu.showStartMenu();

        int choice = utils.inputInt(1,3);

        switch (choice) {
            case 1: initGame(); break;
            case 2: utils.showRules(); break;
            case 3: return;
        }

    }

    private static void initGame() {

        int playersNumber = getNumberOfPlayer();

        players = createPlayers(playersNumber);

        getPlayersName(players);

        play();
    }

    private static void play() {

        Player currentPlayer = players.get(getFirstPlayer());

        out.println(currentPlayer.getName() + " starts first !");

        dices.rollDices(currentPlayer.getRoll(), currentPlayer.getRound());

        dices.show();


    }


    static int getNumberOfPlayer() {

        menu.showNumberOfPlayerMenu();

        int choice = utils.inputInt(1,2);

        if (choice > 1) {
            out.print("\nEnter the number of player: ");
            choice = utils.inputInt(2, 10);
        }

        return choice;
    }

    static List<Player> createPlayers(int number) {

        List<Player> players = new ArrayList<>();

        for (int i=0; i<number; i++) {
            players.add(new Player());
        }

        return players;
    }

    static void getPlayersName(List<Player> players) {

        for (Player player: players) {
            out.print("Player " + (player.getId()+1) + ", enter your name: ");
            String name = utils.inputString();
            if (name != "") {
                player.setName(name);
            }
        }
    }

    static int getFirstPlayer() {
        Random rand = new Random();
        return rand.nextInt(players.size() - 1);
    }

}

