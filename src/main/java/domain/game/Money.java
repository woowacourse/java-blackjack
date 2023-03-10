package domain.game;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = BigDecimal.valueOf(amount);
    }

    public Money(final BigDecimal amount) {
        this.amount = amount;
    }

    private static void validateAmount(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액을 1원 이상 설정해 주세요");
        }
    }

    public Money multiplyProfitWeight(final BigDecimal weight) {
        return new Money(this.amount.multiply(weight));
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
