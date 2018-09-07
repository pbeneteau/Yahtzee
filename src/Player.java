import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Player {

    int roll;
    int round;

    Score score = new Score();

    public Player(int roll, int round) {
        this.roll = roll;
        this.round = round;
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






}

