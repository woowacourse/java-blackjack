package blackjack.domain.participant;

public class BetAmount {

    private final double value;

    public BetAmount(final double value) {
        this.value = value;
        validateNegative(this.value);
    }

    private void validateNegative(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 수 없습니다.");
        }
    }

    public double multiply(final double amplification) {
        return this.value * amplification;
    }

    public double getValue() {
        return this.value;
    }
}
