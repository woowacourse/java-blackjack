package blackjack.domain.participant.state;

public class BetAmount {

    private final double value;

    public BetAmount(final double value) {
        this.value = value;
    }

    public BetAmount multiply(final double profitValue) {
        return new BetAmount(this.value * profitValue);
    }

    public double getValue() {
        return this.value;
    }
}
