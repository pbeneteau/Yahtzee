import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

 /** This class is utilities class like rules or method we use frequently
         */
public class Utils {


    // 1. Create a Scanner using the InputStream available.
    private static Scanner scanner;


    public void showPlayers(List<Player> players) {

        for (Player player: players) {
            player.show();
        }
    }


    // Print the logo of the game
    public void showLogo() {

        String logo = "\n\n /$$     /$$ /$$$$$$  /$$   /$$ /$$$$$$$$ /$$$$$$$$ /$$$$$$$$ /$$$$$$$$\n" +
                "|  $$   /$$//$$__  $$| $$  | $$|__  $$__/|_____ $$ | $$_____/| $$_____/\n" +
                " \\  $$ /$$/| $$  \\ $$| $$  | $$   | $$        /$$/ | $$      | $$      \n" +
                "  \\  $$$$/ | $$$$$$$$| $$$$$$$$   | $$       /$$/  | $$$$$   | $$$$$   \n" +
                "   \\  $$/  | $$__  $$| $$__  $$   | $$      /$$/   | $$__/   | $$__/   \n" +
                "    | $$   | $$  | $$| $$  | $$   | $$     /$$/    | $$      | $$      \n" +
                "    | $$   | $$  | $$| $$  | $$   | $$    /$$$$$$$$| $$$$$$$$| $$$$$$$$\n" +
                "    |__/   |__/  |__/|__/  |__/   |__/   |________/|________/|________/\n";

        out.println(logo);
    }
    // Print the rules of the game Yahtzee
    void showRules () {

        out.print("\n\n\n");
        out.print("   THE RULES OF YAHTZEE:\n\n");
        out.print("   The scorecard used for Yahtzee is composed of two sections. A upper       \n");
        out.print("   section and a lower section. A total of thirteen boxes or thirteen scoring\n");
        out.print("   combinations are divided amongst the sections. The upper section consists \n");
        out.print("   of boxes that are scored by summing the value of the dice matching the    \n");
        out.print("   faces of the box. If a player rolls four 3's, then the score placed in the\n");
        out.print("   3's box is the sum of the dice which is 12. Once a player has chosen to   \n");
        out.print("   score a box, it may not be changed and the combination is no longer in    \n");
        out.print("   play for future rounds. If the sum of the scores in the upper section is  \n");
        out.print("   greater than or equal to 63, then 35 more points are added to the players \n");
        out.print("   overall score as a bonus. The lower section contains a number of poker    \n");
        out.print("   like combinations. See the table provided below:                          \n");
        out.print("\n\n");

        out.print("\n\n");
        out.print("         NAME       |           COMBINATION          |          SCORE\n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("   Three-of-a-kind  | Three dice with the same face  | Sum of all face values\n");
        out.print("                    |                                |     on the 5 dice     \n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("   Four-of-a-kind   |  Four dice with the same face  | Sum of all face values\n");
        out.print("                    |                                |     on the 5 dice     \n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("   Full house       | One pair and a three-of-a-kind |            25         \n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("   Small straight   |     A sequence of four dice    |            30         \n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("   Large straight   |     A sequence of five dice    |            40         \n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("\n\n");


        out.print("\n\n");
        out.print("         NAME       |           COMBINATION          |          SCORE\n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("   Yahtzee (think   |  Five dice with the same face  |            50         \n");
        out.print("   five-of-a-kind)  |                                |                       \n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("   Chance           |  May be used for any sequence  | Sum of all face values\n");
        out.print("                    | of dice; this is the catch all |       on the 5 dice   \n");
        out.print("                    |           combination          |                       \n");
        out.print("   -----------------+--------------------------------+-----------------------\n");
        out.print("\n\n");
    }

    public void show1DArray (int[] array) {
        for (int value: array) {
            out.print(value + " ");
        }
        out.println();
    }

    public void show2DArray (int[][] array) {
        for (int[] row: array) {
            show1DArray(row);
        }
    }

     /**This method is used when we ask some question in menu and permit to input some int choice
      * @param a and @param b are the bounds of possibility in menu
     @return choice of player
     */
    public int inputInt(int a, int b) {

        int input = 0;
        scanner = new Scanner(System.in);

        // secure input
        do {
            try {
                out.print("\nChoice: ");
                input = scanner.nextInt();

            } catch(Exception e)
            {
                input = 0;
                scanner.nextLine();
                out.println("Incorrect Input: " + e);
            }
        } while ( input < a || input > b);
        return input;
    }

    /** This method permit to input some string choice */
    public String inputString() {

        scanner = new Scanner(System.in);

        String input;

        // secure input
        do {
                input = scanner.nextLine();

        } while (input.isEmpty());

        return input;
    }

     /**This method cross the array for each value and return index of the dice
      * @param board is a array of dice and the number of dice we search the index
      @return value of index dice or -1 if dice isn't in the array
      */
    public int getDiceIndex(int[][] board, int dice) {

        for (int[] row : board) {
            for (int value : row) {
                if (value == dice) {
                    return value;
                }
            }
        }
        return -1;
    }


}

