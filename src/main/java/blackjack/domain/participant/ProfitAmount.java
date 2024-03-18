package blackjack.domain.participant;

public class ProfitAmount {

    private final int value;

    public ProfitAmount(int value) {
        this.value = value;
    }

    public static ProfitAmount from(BetAmount betAmount) {
        return new ProfitAmount(betAmount.getValue());
    }

    public ProfitAmount add(ProfitAmount other) {
        return new ProfitAmount(value + other.value);
    }

    public ProfitAmount inverse() {
        return new ProfitAmount(-value);
    }

    public ProfitAmount multiplication(double profitRate) {
        return new ProfitAmount((int) (value * profitRate));
    }

    public int getValue() {
        return value;
    }
}
