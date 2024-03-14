package blackjack.domain;

public class Profit {
    private static final int INVERSE_VALUE = -1;

    private final int value;

    private Profit(int value) {
        this.value = value;
    }

    public static Profit fromWin(Money money) {
        return new Profit(money.getValue());
    }

    public static Profit fromLose(Money money) {
        return new Profit(INVERSE_VALUE * money.getValue());
    }

    public int getValue() {
        return value;
    }
}
