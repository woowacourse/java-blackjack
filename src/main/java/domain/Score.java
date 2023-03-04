package domain;

public class Score {

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score from(final int value) {
        if (value > 21) {
            return new Score(-1);
        }
        return new Score(value);
    }

    public int getValue() {
        return value;
    }

    public boolean isBusted() {
        return value < 0;
    }
}
