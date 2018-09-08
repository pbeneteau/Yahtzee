


public class Dice implements Comparable<Dice>{


    private int value;
    private boolean dropped;

    public Dice(int value, boolean dropped) {
        this.dropped = dropped;
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int dice) {
        this.value = dice;
    }

    public boolean isDropped() {
        return dropped;
    }

    public void setDropped(boolean dropped) {
        this.dropped = dropped;
    }

    public int compareTo(Dice o) {
        return this.value - o.value;
    }
}
