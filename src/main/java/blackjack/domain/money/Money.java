package blackjack.domain.money;

public class Money {

    private final int value;

    public Money(int value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("금액은 양수이어야 합니다.");
        }
    }
}
