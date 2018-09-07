import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

public class Dices {


    private int[][] board;


    public Dices() {
        initBoard();
    }


    // We create a score Arrays on 2 dimensions
    private void initBoard() {

        board = new int[6][3];

        IntStream.range(0, board.length).forEach(i -> {
            board[i][0] = i + 1; // first column is dices
            board[i][1] = 0; // second column is number of dices do you have after roll
            board[i][2] = 0; // three column is when you freeze dices like keep dices
        });
    }

    // Roll dices by generating random new dices
    private void rollDices(int roll, int round) {

        out.println("\n\n****************************** ROUND " + (round+1) + " ******************************");
        out.println("****************************** DROP  " + (roll+1) + " ******************************\n");

        int dices[] = getRandomDices(numberDicesReload());

        fillBoardDices(dices);
    }

    // Fill the dices board with the randomly generated dices
    private void fillBoardDices(int[] randomDices) {

        // if dice not kept, set number to 0
        IntStream.range(0, board.length).filter(i -> board[i][2] != 1).forEach(i -> board[i][1] = 0);

        for (int randomDice : randomDices) {

            // set random dices values
            IntStream.range(0, board.length).filter(j -> randomDice == board[j][0]).forEach(j -> board[j][1] += 1);
        }
    }

    // Create random numbers dices between 1-6
    private int getRandomDice() {

        Random rand = new Random();
        int randomDice = rand.nextInt(6) + 1;

        return randomDice;
    }

    // Randomly generate dices
    private int[] getRandomDices(int number) {

        int[] dices = new int[number];

        for (int i=0; i<number; i++) {

            dices[i] = getRandomDice();
        }
        Arrays.sort(dices);

        return dices;
    }

    // Return the number of dices to roll
    private int numberDicesReload() {

        int nbrDices = 5;

        for (int[] aBoard : board) {

            if (aBoard[2] == 1) {
                nbrDices -= aBoard[1];
            }
        }
        return nbrDices;
    }

    // Makes dices as kept to ensure they won't be rolled
    private void keepDices() {

        for (int i=0; i<board.length; i++) {

            if (board[i][2] == 1) {
                board[i][2] = 0;
                break;
            }
        }

        int diceToFreeze = selectDices() - 1;

        board[diceToFreeze][2] = 1;
    }

    // Select Dices to keep
    private int selectDices() {

        int select;

        do {
            out.print("Select the dices you wanna kept: ");

            Scanner key;
            key = new Scanner(in);
            select = key.nextInt();

        } while(board [select-1][1] == 0 ); // secure entry

        return select;
    }

    // Print dices that have been rolled
    private void showDices() {

        String top =        " _______ ";
        String row1left =   "| o     |";
        String row1Right =  "|     o |";
        String row2 =       "| o   o |";
        String row1Mid =    "|   o   |";
        String row0 =       "|       |";
        String bot =        " ------- ";
        String frozen =     "    *    ";
        String space =      "         ";
        String used =       "    x    ";

        String[] dice1 = {top, row0, row1Mid, row0, bot, frozen};
        String[] dice2 = {top, row1Right, row0, row1left, bot, frozen};
        String[] dice3 = {top, row1Right, row1Mid, row1left, bot, frozen};
        String[] dice4 = {top, row2, row0, row2, bot, frozen};
        String[] dice5 = {top, row2, row1Mid, row2, bot, frozen};
        String[] dice6 = {top, row2, row2, row2, bot, frozen};

        String[][] dices = {dice1, dice2, dice3, dice4, dice5, dice6};

        out.println("\nThe dices have been rolled !\n");

        for (int i = 0; i < dices[0].length; i++) {

            for (int j = 0; j < dices.length; j++) {

                if (board[j][1] > 0) {

                    for (int n=0; n<board[j][1]; n++) {

                        if (i == 5 && board[j][2] == 1) {
                            out.print(dices[j][i] + "\t");
                        } else if (i == 5) {
                            out.print(space + "\t");
                        } else {
                            out.print(dices[j][i] + "\t");
                        }
                    }
                }
            }
            out.println();
        }
        out.println("\n");
    }


    // Drop the dices: stop keeping them and allow them to be rolled
    private void dropDices() {

        out.println("Enter the dices you want to drop (separated by comma): ");

        Scanner kbd = new Scanner(System.in);
        List<Integer> dicesToDrop = new ArrayList<>();
        String line;
        String[] lineVector;

        line = kbd.nextLine(); //read 1,2,3

        //separate all values by comma
        lineVector = line.split(",");

        for (String lineValue: lineVector) {
            int dice = Integer.parseInt(lineValue);

            dicesToDrop.add(dice);
        }

        for (int i = 0; i<board.length; i++) {

            if(board[i][2] == 1) { // if the dice is kept ...
                board[i][2] = 0;
                break;
            }
        }
    }

}


