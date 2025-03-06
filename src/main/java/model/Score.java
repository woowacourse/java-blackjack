package model;

public class Score implements Comparable {
    private final int value;

    public Score(int score) {
        this.value = score;
    }

    public boolean isBust() {
        return value > 21;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(value, ((Score) o).value);
    }

    public Score plus(int sum) {
        return new Score(value + sum);
    }

    public Score minus(int sum) {
        return new Score(value - sum);
    }

    public int getValue() {
        return value;
    }
}
