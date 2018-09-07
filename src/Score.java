import java.util.stream.IntStream;

import static java.lang.System.out;

public class Score {


    private static int[][] scores;



    // We create a score Arrays on 2 dimensions
    private static void initScores() {

        scores = new int[6][2];

        IntStream.range(0, scores.length).forEach(i -> {
            scores[i][0] = i + 1; // first column is dices
            scores[i][1] = 0;     // second column of score point
        });
    }

    // Check if all dices are already in the score table
    private static boolean dicesAlreadyInScores(int[][] board ) {

        for (int[] dice: board) {
            if (dice[1] > 0 && !diceAlreadyInScores(dice[0])) { // if dice isn't score board
                return false;
            }
        }
        return true;
    }

    // Check if a dice is already in the score table
    private static boolean diceAlreadyInScores(int dice) {

        if (scores[dice - 1][1] > 0) return true;
        return false;
    }


    // Add score in score board at the end of each round
    private static void addPointsToScore(int[][] board) {

        int points;

        for (int[] aBoard : board)
            if (aBoard[2] == 1) {
                points = aBoard[0] * aBoard[1];
                scores[aBoard[0] - 1][1] = points;
                out.println("\n" + points + " points added to you total score !");
                return;
            }
    }

    // Check if all points have been init
    private static boolean isGameEnded() {

        for (int[] score : scores) {
            if (score[1] == 0) {
                return false;
            }
        }
        return true;
    }

    // Calculate the total score of the player (end of the game)
    private static int getTotalScore() {

        int sum = 0;

        for (int[] score : scores) {
            sum += score[1];
        }

        if (sum >= 63) { // bonus
            sum += 35;
        }
        return sum;
    }

    // Print the game score
    private static void showScore() {

        out.println("\n• UPPER SECTION •\n");
        out.println("Dices   Points");
        for (int[] score : scores) {
            out.println(score[0] + ":      " + score[1]);
        }
    }


}
