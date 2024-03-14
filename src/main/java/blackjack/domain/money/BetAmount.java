package blackjack.domain.money;

public class BetAmount {

    private final int value;

    public BetAmount(int value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수이어야 합니다.");
        }
    }

    public int toInt() {
        return value;
    }
}
