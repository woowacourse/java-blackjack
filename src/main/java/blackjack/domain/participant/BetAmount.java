package blackjack.domain.participant;

public class BetAmount {

    private final double value;

    private BetAmount(final double value) {
        this.value = value;
    }

    public static BetAmount betting(final double value) {
        BetAmount betAmount = new BetAmount(value);
        validateNegative(betAmount.value);
        return betAmount;
    }

    private static void validateNegative(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 수 없습니다.");
        }
    }

    public BetAmount multiply(final double amplification) {
        return new BetAmount(this.value * amplification);
    }

    public double getValue() {
        return this.value;
    }
}
