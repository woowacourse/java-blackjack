package blackjack.result;

import java.util.List;
import java.util.Objects;

public class Money {

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public static Money fromBettingAmount(int amount) {
        validateNegative(amount);
        return Money.from(amount);
    }

    public static Money fromProfitOrLoss(int amount) {
        return Money.from(amount); // 음수 허용
    }

    public static Money sumOf(List<Money> moneys) {
        return moneys.stream()
                .reduce(Money.from(0), Money::plus);
    }

    private static void validateNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 음수가 될 수 없습니다.");
        }
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money applyProfitRate(int profitPercent) {
        int percent = 100;
        return Money.fromProfitOrLoss(amount * profitPercent / percent);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
