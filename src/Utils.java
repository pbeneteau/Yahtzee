import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

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

    // Print the game rules
    public void showRules() {

        String rules = "\n\n1° Object of the game\n" +
                "\n" +
                "The object of Yahtzee is to obtain the highest score from throwing 5 dice.\n" +
                "The game consists of 13 rounds. In each round, you roll the dice and then score the roll in one of 13 categories. You must score once in each category. The score is determined by a different rule for each category.\n" +
                "The game ends once all 13 categories have been scored.\n" +
                "\n" +
                "2° Game Start\n" +
                "\n" +
                "To start with, roll all the dice. After rolling you can either score the current roll (see below), or re-roll any or all of the dice.\n" +
                "" +
                "You may only roll the dice a total of 3 times. After rolling 3 times you must choose a category to score.\n" +
                "" +
                "You may score the dice at any point in the round, i.e. it doesn't have to be after the 3rd roll.\n" +
                "\n" +
                "3° Scoring\n" +
                "\n" +
                "To score your combination of 5 dice, you click one of the 13 boxes, or write it on the scorecard (scoresheet). There are two sections to the score table - the Upper Section and the Lower Section.\n" +
                "" +
                "Once a box has been scored, it cannot be scored again for the rest of the game (except the Yahtzee category), so choose wisely.\n" +
                "\n" +
                "4° Upper Section Scoring\n" +
                "\n" +
                "If you score in the upper section of the table, your score is the total of the specified die face.\n" +
                "So if you roll:\n" +
                "5 - 2 - 5 - 6 - 5 and score in the Fives category, your total for the category would be 15, because there are three fives, which are added together.\n" +
                "If the One, Three or Four Categories were selected for scoring with this roll, you would score a zero.\n" +
                "If placed in the Two or Six category, you would score 2 and 6 respectively.\n" +
                "\n" +
                "Bonus If the total of Upper scores is 63 or more, add a bonus of 35. Note that 63 is the total of three each of 1s, 2s, 3s, 4s, 5s and 6s." +
                "\n" +
                "*: dices you kept" +
                "\n" +
                "x: dices you can't keep (already in the upper case)";

        out.println(rules);
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

    public int inputInt(int a, int b) {

        scanner = new Scanner(System.in);

        // Secure
        int input = scanner.nextInt();
        return input;
    }

    public String inputString() {

        scanner = new Scanner(System.in);
        // Secure
        String input = scanner.nextLine();
        return input;
    }



}
