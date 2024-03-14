package blackjack.domain.money;

public class BetAmount {

    private final int value;

    public BetAmount(int value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("금액은 양수이어야 합니다.");
        }
    }

    public BetAmount add(BetAmount betAmount) {
        return new BetAmount(this.value + betAmount.value);
    }

    public BetAmount multiply(double multiplier) {
        int newValue = (int) Math.ceil(this.value * multiplier);
        return new BetAmount(newValue);
    }

    public int toInt() {
        return value;
    }
}
