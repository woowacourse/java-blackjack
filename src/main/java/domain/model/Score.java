package domain.model;

public class Score {

    private int value;

    public Score(final int value) {
        this.value = value;
    }

    public void add(final int additionalValue) {
        this.value += additionalValue;
    }

    public int getValue() {
        return value;
    }
}
