package blackjack.domain.participant;

public class Profit {

    private final int value;

    private Profit(int value) {
        this.value = value;
    }

    public static Profit from(AmountOfBet amountOfBet) {
        return new Profit(amountOfBet.getValue());
    }

    public static Profit from(int value) {
        return new Profit(value);
    }

    public Profit add(Profit other) {
        return new Profit(value + other.value);
    }

    public Profit inverse() {
        return new Profit(-value);
    }

    public int getValue() {
        return value;
    }

    public Profit multiplication(double profitRate) {
        return new Profit((int) (value * profitRate));
    }
}
