
import static java.lang.System.*;
import java.util.Scanner;

public class Menu {

    /**
     This method show the Start Menu of the game
     @return nothing because void method
     */
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

    /**
     This method show the type of the game
     @return nothing because void method
     */
    public void showNumberOfPlayerMenu() {

            out.println("\nGame Mode");

            out.println("1. Solo");
            out.println("2. Multi-player");

    }

   /** This method show all actions player can made in the game
     @return nothing because void method
     */
    public void showRollActionMenu(Player player, boolean lastRoll) {

        if (!lastRoll) {

            out.println("Action (roll " + (player.getRoll()+1) + ")"); // Action in roll

            out.println("1. Hold dices"); // can select dices
            out.println("2. Drop dices"); // can drop one or some dices
            out.println("3. Roll the dices"); // can roll dices
            out.println("4. Select a pattern"); // can choose what kind of pattern he would like to do
            out.println("5. Show points"); // can look board score

        } else {
            out.println("\nAction (roll " + player.getRoll()+1 + ")");

            out.println("1. Select a pattern"); // same choice 4
            out.println("2. Show points"); // same choice 5
        }

    }
}
