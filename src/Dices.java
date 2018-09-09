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
                                out.print("   *" + count + "*   " + "\t");
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

            out.println(value - 1);
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


    public int checkPoints(Player player) {

        Score playerScore = player.getScore();

        int[][] upperSection = playerScore.getUpperSection();
        int[][] lowerSection = playerScore.getLowerSection();

        int[] aDice = new int[5];
        int choice;
        int score;

        for (int i=0; i<aDice.length; i++) {
            aDice[i] = dices.get(i).getValue();
        }

        System.out.println("Choose your pattern: ");

        System.out.println("1 - yahtzee");
        if (lowerSection[0][2] == 0) {
            System.out.println("2 - full house");
        }
        if (lowerSection[0][4] == 0) {
            System.out.println("3 - large straight");
        }
        if (lowerSection[0][3] == 0) {
            System.out.println("4 - small straight");
        }
        if (lowerSection[0][1] == 0) {
            System.out.println("5 - four of a kind");
        }
        if (lowerSection[0][0] == 0) {
            System.out.println("6 - three of a kind");
        }
        if (upperSection[0][1] == 0) {
            System.out.println("7 - number of 1's");
        }
        if (upperSection[0][1] == 0) {
            System.out.println("8 - number of 2's");
        }
        if (upperSection[0][1] == 0) {
            System.out.println("9 - number of 3's");
        }
        if (upperSection[0][1] == 0) {
            System.out.println("10 - number of 4's");
        }
        if (upperSection[0][1] == 0) {
            System.out.println("11 - number of 5's");
        }
        if (upperSection[0][1] == 0) {
            System.out.println("12 - number of 6's");
        }
        if (lowerSection[0][6] == 0) {
            System.out.println("13 - chance");
        }

        Utils utils = new Utils();

        out.print("Choice: ");
        choice = utils.inputInt(1, 15);

        int x, y, winings = 0, winingsa = 0;
        int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
        Arrays.sort(aDice);

        // Numbers
        for (y = 0; y < 5; y++) {
            if (aDice[y] == 1) {
                ones++;
            }
            if (aDice[y] == 2) {
                twos++;
            }
            if (aDice[y] == 3) {
                threes++;
            }
            if (aDice[y] == 4) {
                fours++;
            }
            if (aDice[y] == 5) {
                fives++;
            }
            if (aDice[y] == 6) {
                sixes++;
            }
        }

        // Straights
        if ((aDice[0] == aDice[1] - 1) && (aDice[1] == aDice[2] - 1)
                && (aDice[2] == aDice[3] - 1) && (aDice[3] == aDice[4] - 1)
                && (choice == 3)) {
            winingsa = 1;
        } else if ((ones > 0) && (twos > 0) && (threes > 0) && (fours > 0)) {
            winingsa = 2;
        } else if ((threes > 0) && (fours > 0) && (fives > 0) && (sixes > 0)) {
            winingsa = 2;
        } else if ((twos > 0) && (threes > 0) && (fours > 0) && (fives > 0)) {
            winingsa = 2;
        }

        //Pairs
        for (x = 0; x < 5; x++) {
            if (x != 0) {
                if ((aDice[0] == aDice[x])) {
                    winings++;
                }
            }
            if ((x != 0) && (x != 1)) {
                if ((aDice[1] == aDice[x])) {
                    winings++;
                }
            }
            if ((x != 0) && (x != 1) && (x != 2)) {
                if ((aDice[2] == aDice[x])) {
                    winings++;
                }
            }
            if ((x != 0) && (x != 1) && (x != 2) && (x != 3)) {
                if ((aDice[3] == aDice[x])) {
                    winings++;
                }
            }
        }

        if ((winingsa == 1) && (choice == 3)) {
            System.out.println("You have a large straight.");
            score = 40;
            lowerSection[4][1] = score;
            return score;
        } else if ((winingsa == 2) && (choice == 4)) {
            System.out.println("You have a small straight.");
            score = 30;
            lowerSection[3][1] = score;
            return score;
        } else if ((winings == 10) && (choice == 1)) {

            System.out.println("Yahtzee !");

            if (lowerSection[5][1] == -1) {
                score = 50;
            } else if (lowerSection[5][1] == 0) {
                score = 0;
            } else {
                score = lowerSection[5][1] + 100;
            }
            lowerSection[5][1] = score;
            return score;
        } else if ((choice == 6) && (winings >= 3)) {
            System.out.println("You have three of a kind.");
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
            lowerSection[0][1] = score;
            return score;
        } else if ((winings == 4) && (choice == 2)) {
            System.out.println("You have a full house.");
            score = 25;
            lowerSection[2][1] = score;
            return score;
        } else if ((winings >= 6) && (choice == 5)) {
            System.out.println("You have four of a kind.");
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
            lowerSection[1][1] = score;
            return score;
        } else if (choice == 7) {
            System.out.println("You have " + ones + " ones.");
            score = ones;
            upperSection[0][1] = score;
            return score;
        } else if (choice == 8) {
            System.out.println("You have " + twos + " twos.");
            score = twos * 2;
            upperSection[1][1] = score;
            return score;
        } else if (choice == 9) {
            System.out.println("You have " + threes + " threes.");
            score = threes * 3;
            upperSection[2][1] = score;
            return score;
        } else if (choice == 10) {
            System.out.println("You have " + fours + " fours.");
            score = fours * 4;
            upperSection[3][1] = score;
            return score;
        } else if (choice == 11) {
            System.out.println("You have " + fives + " fives.");
            score = fives * 5;
            upperSection[4][1] = score;
            return score;
        } else if (choice == 12) {
            System.out.println("You have " + sixes + " sixes.");
            score = sixes * 6;
            upperSection[5][1] = score;
            return score;
        } else if (choice == 13) {
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
            System.out.println("Your get " + score + " points with the chance.");
            lowerSection[6][1] = score;
            return score;
        } else {
            System.out.println("You got nothing.");
            score = 0;
            return score;
        }
    }


}

