


import static java.lang.System.out;

public class Player {

    private static int count = 0;
    private int id;

    private int roll;
    private int round;
    private String name;

    private Score score;

    public Player() {
        this.roll = 0;
        this.round = 0;
        this.id = count++;
        this.score = new Score();
    }

    // MARK: Getters and Setters

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


    // MARK: Methods

    // This method show the score of each player, the roll and category points
    public void show() {

        out.println("\n\n-----------");
        out.println(this.name + " score: ");
        out.println("-----------");
        out.println("\nROLL: "+ (this.roll+1) + "      CATEGORY                  Points\n");

        score.show();
    }


}

