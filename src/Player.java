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

    Score score = new Score();

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

    public void show() {

        out.println();

        out.println(this.name + " Stats:");

        out.println("stats ...\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

