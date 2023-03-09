package blackjack.domain.betting;

public class Profit {

    private final int value;

    public Profit(final int value) {
        this.value = value;
    }

    public Profit increaseByPercent(final int percent) {
        return new Profit(value + (int) (value * percent / 100.0));
    }

    public Profit loss() {
        if (value > 0) {
            return new Profit(-value);
        }
        return this;
    }

    public int getValue() {
        return value;
    }
}
