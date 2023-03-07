package blackjack.domain.participant;

public class Money {
    private final int value;

    public Money(final int value) {
        validate(value);
        this.value = value;
    }

    public Money earn(final Money money) {
        validateEarnValueIsNegative(money);
        return new Money(this.value + money.value);
    }

    public Money spend(final Money money) {
        validateEnoughMoney(money);
        return new Money(this.value - money.value);
    }

    public int getValue() {
        return value;
    }

    private void validate(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("생성시 입력값이 음수가 될 수 없습니다.");
        }
    }

    private void validateEarnValueIsNegative(final Money money) {
        if (money.value < 0) {
            throw new IllegalArgumentException("번 금액이 음수일 수 없습니다.");
        }
    }

    private void validateEnoughMoney(final Money money) {
        if (this.value - money.value < 0) {
            throw new IllegalArgumentException("지불할 현금이 부족합니다.");
        }
    }
}
