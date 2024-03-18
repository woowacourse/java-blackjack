package blackjack.domain.cardgame;

public class BettingAmount {
    private final int value;

    public BettingAmount(final int value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[ERROR] 0보다 큰 값만 입력할 수 있습니다.");
        }
    }
}
