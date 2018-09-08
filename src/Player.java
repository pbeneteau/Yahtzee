import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Player {

    private static int count = 0;
    private int id;

    private int roll;
    private int round;
    private String name;

    private Score score = new Score();
    private int[] wins = new int[15];

    public Player() {
        this.roll = 0;
        this.round = round;
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    public int getRoll() {
        return roll;
    }

    public int getRound() {
        return round;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int[] getWins() {
        return wins;
    }

    public void setWins(int[] wins) {
        this.wins = wins;
    }

    public void show() {

        out.println("\n\n" + this.name + "scores: ");
        out.println("\n             ROLL:"+ this.roll + "             CATEGORY            PLAYER 1\n");
        out.println("                          [   ] ONES                [      ]");
        out.println("                          [   ] TWOS                [      ]");
        out.println("                          [   ] THREES              [      ]");
        out.println("                          [   ] FOURS               [      ]");
        out.println("                          [   ] FIVES               [      ]");
        out.println("                          [   ] SIXES               [      ]");

        out.println("                          [   ]  THREE OF A KIND    [      ]");
        out.println("                          [   ]  FOUR OF A KIND     [      ]");
        out.println("                          [   ]  FULL HOUSE(25)     [      ]");
        out.println("                          [   ]  SMALL STRAIGHT(30) [      ]");
        out.println("                          [   ]  LARGE STRAIGHT(40) [      ]");
        out.println("                          [   ]  YAHTZEE!(50)       [      ]");
        out.println("                          [   ]  CHANCE             [      ]\n\n");

        out.println("                                TOTAL               [      ]");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}

