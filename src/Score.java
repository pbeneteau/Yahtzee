import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import static java.lang.System.*;


public class Score {

    private int[][] upperSection;
    private int[][] lowerSection;

    public Score() {
        initSections();
    }

    // Getters
    public int[][] getUpperSection() {
        return upperSection;
    }

    public int[][] getLowerSection() {
        return lowerSection;
    }

    // Setters
    public void setUpperSection(int[][] upperSection) {
        this.upperSection = upperSection;
    }

    public void setLowerSection(int[][] lowerSection) {
        this.lowerSection = lowerSection;
    }

    private void initSections() {

        upperSection = new int[6][2];
        lowerSection = new int[7][2];

        IntStream.range(0, upperSection.length).forEach(i -> {
            upperSection[i][0] = i + 1;
            upperSection[i][1] = 0;

        });

        IntStream.range(0, lowerSection.length).forEach(i -> {
            lowerSection[i][0] = i + 1;

            if (i == 5) {
                lowerSection[i][1] = -1;
            }
        });
    }

    public void show() {

        out.println("             ONES                 [ " + upperSection[0][1] + " ]");
        out.println("             TWOS                 [ " + upperSection[1][1] + " ]");
        out.println("             THREES               [ " + upperSection[2][1] + " ]");
        out.println("             FOURS                [ " + upperSection[3][1] + " ]");
        out.println("             FIVES                [ " + upperSection[4][1] + " ]");
        out.println("             SIXES                [ " + upperSection[5][1] + " ]");

        out.println("             THREE OF A KIND      [ " + lowerSection[0][1] + " ]");
        out.println("             FOUR OF A KIND       [ " + lowerSection[1][1] + " ]");
        out.println("             FULL HOUSE (25)      [ " + lowerSection[2][1] + " ]");
        out.println("             SMALL STRAIGHT (30)  [ " + lowerSection[3][1] + " ]");
        out.println("             LARGE STRAIGHT (40)  [ " + lowerSection[4][1] + " ]");
        if (lowerSection[5][1] == -1) {
            out.println("             YAHTZEE! (50)        [ " + 0 + " ]");
        } else {
            out.println("             YAHTZEE! (50)        [ " + lowerSection[5][1] + " ]");
        }
        out.println("             CHANCE               [ " + lowerSection[6][1] + " ]\n\n");

        out.println("             TOTAL                [ " + getTotalScore() + " ]\n");
    }


    private int getTotalScore() {

        int upper_section_total = 0;
        int lower_section_total = 0;

        for (int[] row : upperSection) {
            upper_section_total += row[1];
        }

        if (upper_section_total >= 63) upper_section_total += 35;

        for (int[] row : lowerSection) {

            if (row[1] != -1) { lower_section_total += row[1]; }
        }

        return upper_section_total + lower_section_total;
    }

    public void setPatternPoints(List<Dice> dices) {

        int choice = getUserPatternChoice();

        int points = getPatternPoints(dices, choice);

        out.println(" You earned " + points + " points !");
    }

    public int getUserPatternChoice() {

        int choice;

        System.out.println("Choose your pattern: ");

        System.out.println("1 - yahtzee");
        if (lowerSection[2][1] == 0) {
            System.out.println("2 - full house");
        }
        if (lowerSection[4][1] == 0) {
            System.out.println("3 - large straight");
        }
        if (lowerSection[3][1] == 0) {
            System.out.println("4 - small straight");
        }
        if (lowerSection[1][1] == 0) {
            System.out.println("5 - four of a kind");
        }
        if (lowerSection[0][1] == 0) {
            System.out.println("6 - three of a kind");
        }
        if (upperSection[0][1] == 0) {
            System.out.println("7 - number of 1's");
        }
        if (upperSection[1][1] == 0) {
            System.out.println("8 - number of 2's");
        }
        if (upperSection[2][1] == 0) {
            System.out.println("9 - number of 3's");
        }
        if (upperSection[3][1] == 0) {
            System.out.println("10 - number of 4's");
        }
        if (upperSection[4][1] == 0) {
            System.out.println("11 - number of 5's");
        }
        if (upperSection[5][1] == 0) {
            System.out.println("12 - number of 6's");
        }
        if (lowerSection[6][1] == 0) {
            System.out.println("13 - chance");
        }

        Utils utils = new Utils();

        out.print("Choice: ");
        choice = utils.inputInt(1, 15);

        return choice;
    }

    public int getPatternPoints(List<Dice> dices, int choice) {

        out.println();

        int[] aDice = new int[5];
        int score;

        for (int i=0; i<aDice.length; i++) {
            aDice[i] = dices.get(i).getValue();
        }

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
            System.out.print("You have a large straight.");
            score = 40;
            lowerSection[4][1] = score;
            return score;
        } else if ((winingsa == 2) && (choice == 4)) {
            System.out.print("You have a small straight.");
            score = 30;
            lowerSection[3][1] = score;
            return score;
        } else if ((winings == 10) && (choice == 1)) {

            System.out.print("Yahtzee !");

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
            System.out.print("You have three of a kind.");
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
            lowerSection[0][1] = score;
            return score;
        } else if ((winings == 4) && (choice == 2)) {
            System.out.print("You have a full house.");
            score = 25;
            lowerSection[2][1] = score;
            return score;
        } else if ((winings >= 6) && (choice == 5)) {
            System.out.print("You have four of a kind.");
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
            lowerSection[1][1] = score;
            return score;
        } else if (choice == 7) {
            System.out.print("You have " + ones + " ones.");
            score = ones;
            upperSection[0][1] = score;
            return score;
        } else if (choice == 8) {
            System.out.print("You have " + twos + " twos.");
            score = twos * 2;
            upperSection[1][1] = score;
            return score;
        } else if (choice == 9) {
            System.out.print("You have " + threes + " threes.");
            score = threes * 3;
            upperSection[2][1] = score;
            return score;
        } else if (choice == 10) {
            System.out.print("You have " + fours + " fours.");
            score = fours * 4;
            upperSection[3][1] = score;
            return score;
        } else if (choice == 11) {
            System.out.print("You have " + fives + " fives.");
            score = fives * 5;
            upperSection[4][1] = score;
            return score;
        } else if (choice == 12) {
            System.out.print("You have " + sixes + " sixes.");
            score = sixes * 6;
            upperSection[5][1] = score;
            return score;
        } else if (choice == 13) {
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
            System.out.print("Your get " + score + " points with the chance.");
            lowerSection[6][1] = score;
            return score;
        } else {
            System.out.print("You got nothing.");
            score = 0;
            return score;
        }
    }

}
