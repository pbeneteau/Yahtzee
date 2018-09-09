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

            for (int j=1; j<upperSection[i].length - 1; j++) {
                upperSection[i][j] = 0;
            }
        });

        IntStream.range(0, lowerSection.length).forEach(i -> {
            lowerSection[i][0] = i + 1;

            for (int j=1; j<upperSection[i].length - 1; j++) {
                upperSection[i][j] = 0;
            }
        });
    }

}
