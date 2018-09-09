import java.security.DigestException;
import java.util.*;
import java.util.stream.IntStream;


import static java.lang.System.*;


public class Dices implements YahtzeeConstants {


    private List<Dice> dices = new ArrayList<>();


    public Dices() {
        initDices();
    }


    private void initDices() {

        for (int i=0; i<5; i++) {
            Dice dice = new Dice(1, true);
            dices.add(dice);
        }
    }

    // Roll dices by generating random new dices
    public void rollDices(boolean forceRollAll) {

        for (Dice dice : dices) {

            if (!forceRollAll) {
                if (dice.isDropped()) {
                    Dice newDice = dice;
                    dice.setValue(getRandomDice());
                    dices.set(dices.indexOf(dice), newDice);
                }
            } else {
                Dice newDice = dice;
                dice.setValue(getRandomDice());
                dice.setDropped(true);
                dices.set(dices.indexOf(dice), newDice);
            }
        }

        Collections.sort(dices);

        out.println("\nThe dices have been rolled !\n");
    }

    // Create random numbers dices between 1-6
    private int getRandomDice() {

        Random rand = new Random();
        int randomDice = rand.nextInt(6) + 1;

        return randomDice;
    }

    // Print dices that have been rolled
    public void show() {

        String top =        " _______ ";
        String row1left =   "| o     |";
        String row1Right =  "|     o |";
        String row2 =       "| o   o |";
        String row1Mid =    "|   o   |";
        String row0 =       "|       |";
        String bot =        " ------- ";
        String frozen =     "    *    ";
        String space =      "         ";

        String[] dice1 = {top, row0, row1Mid, row0, bot, space};
        String[] dice2 = {top, row1Right, row0, row1left, bot, space};
        String[] dice3 = {top, row1Right, row1Mid, row1left, bot, space};
        String[] dice4 = {top, row2, row0, row2, bot, space};
        String[] dice5 = {top, row2, row1Mid, row2, bot, space};
        String[] dice6 = {top, row2, row2, row2, bot, space};

        String[][] dicesPattern = {dice1, dice2, dice3, dice4, dice5, dice6};

        int count = 1;

        for (int i = 0; i < dicesPattern[0].length; i++) {

            for (int j = 0; j < dicesPattern.length; j++) {

                for (Dice dice : dices) {

                    if (dice.getValue() == j + 1) {

                        if (i < 5) {

                            out.print(dicesPattern[j][i] + "\t");

                        } else if (i == 5) {

                            if (dice.isDropped()) {
                                out.print("    " + count + "    " + "\t");
                            } else {
                                out.print("   |" + count + "|   " + "\t");
                            }
                            count++;
                        }
                    }
                }
            }
            out.println();
        }
        out.println("\n");
    }

    private Dice dicesContains(int value) {

        for (Dice dice: dices) {
            if (dice.getValue() == value) {
                return dice;
            }
        }
        return null;
    }

    // Drop the dices: stop keeping them and allow them to be rolled
    public void holdDices() {

        out.print("Enter the dices you want to hold separated by comma (ex: 1,4): ");

        Scanner kbd = new Scanner(System.in);
        List<Integer> dicesToHold = new ArrayList<>();
        String line;
        String[] lineVector;

        line = kbd.nextLine(); //read 1,2,3

        //separate all values by comma
        lineVector = line.split(",");

        for (String lineValue: lineVector) {
            int dice = Integer.parseInt(lineValue);

            dicesToHold.add(dice);
        }

        for (int value: dicesToHold) {
            dices.get(value - 1).setDropped(false);
        }
    }


    // Drop the dices: stop keeping them and allow them to be rolled
    public void dropDices() {

        out.print("Enter the dices you want to drop separated by comma (ex: 1,4): ");

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

        for (int value: dicesToDrop) {
            dices.get(value - 1).setDropped(true);
        }
    }


    public List<Dice> getDices() {
        return dices;
    }




}

