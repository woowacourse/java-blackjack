package blackjack.domain.participant;

public class Money {
    private final int value;

    public Money(final int value) {
        validate(value);
        this.value = value;
    }

    public Money earn(final int value) {
        validateEarnValueIsNegative(value);
        return new Money(this.value + value);
    }

    public Money spend(final int value) {
        validateEnoughMoney(value);
        return new Money(this.value - value);
    }

    public int getValue() {
        return value;
    }

    private void validate(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("생성시 입력값이 음수가 될 수 없습니다.");
        }
    }

    private void validateEarnValueIsNegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("번 금액이 음수일 수 없습니다.");
        }
    }

    private void validateEnoughMoney(final int value) {
        if (this.value - value < 0) {
            throw new IllegalArgumentException("지불할 현금이 부족합니다.");
        }
    }
}
