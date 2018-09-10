
import static java.lang.System.*;
import java.util.Scanner;

public class Menu {


    public void showStartMenu() {


        // Display menu graphics
            out.println("\n\t\t\t\t\t============================");
            out.println("\t\t\t\t\t|           START          |");
            out.println("\t\t\t\t\t============================");
            out.println("\t\t\t\t\t| 1. Play                  |");
            out.println("\t\t\t\t\t| 2. Rules                 |");
            out.println("\t\t\t\t\t| 3. Exit                  |");
            out.println("\t\t\t\t\t============================");


    }

    public void showNumberOfPlayerMenu() {

            out.println("\nGame Mode");

            out.println("1. Solo");
            out.println("2. Multi-player");

    }

    public void showRollActionMenu(Player player, boolean lastRoll) {

        if (!lastRoll) {

            out.println("Action (roll " + (player.getRoll()+1) + ")");

            out.println("1. Hold dices");
            out.println("2. Drop dices");
            out.println("3. Roll the dices");
            out.println("4. Select a pattern");
            out.println("5. Show points");

        } else {
            out.println("\nAction (roll " + player.getRoll()+1 + ")");

            out.println("1. Select a pattern");
            out.println("2. Show points");
        }

    }
}
