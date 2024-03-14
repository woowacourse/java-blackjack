package blackjack.domain.betting;

public class Money {
    private final int value;

    private Money(final int value) {
        validateValue(value);
        this.value = value;
    }

    public Money(final String value) {
        this(Integer.parseInt(value));
    }

    private void validateValue(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈의 액수는 0 이상이어야 합니다.");
        }
    }

    public int getValue() {
        return this.value;
    }
}
