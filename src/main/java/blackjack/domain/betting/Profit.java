package blackjack.domain.betting;

public class Profit {

    private final int value;

    public Profit(final int value) {
        this.value = value;
    }

    public Profit increaseFiftyPercent() {
        return new Profit(value + (int) (value * 0.5));
    }

    public Profit reverse() {
        return new Profit(-value);
    }

    public int getValue() {
        return value;
    }
}
