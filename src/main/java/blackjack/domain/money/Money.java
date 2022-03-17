package blackjack.domain.money;

import blackjack.utils.IntegerUtils;

public class Money {

    private final int amount;

    public Money(String input) {
        this(IntegerUtils.parseInt(input));
    }

    public Money(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0원 이하가 될 수 없습니다.");
        }
    }

    public Money multiply(double rate) {
        return new Money((int) (this.amount * rate));
    }

    public int getAmount() {
        return amount;
    }
}
