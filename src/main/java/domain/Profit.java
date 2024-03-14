package domain;

public class Profit {

    private static final double MULTIPLE_OF_BLACKJACK = 1.5;

    private final int value;

    public Profit(int value) {
        this.value = value;
    }

    public Profit lose() {
        return new Profit(-value);
    }

    public Profit keep() {
        return new Profit(0);
    }

    public Profit win() {
        return new Profit(value);
    }

    public Profit specialWin() {
        return new Profit((int) (value * MULTIPLE_OF_BLACKJACK));
    }

    public int getValue() {
        return value;
    }
}
