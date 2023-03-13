package blackjack.domain.game;

import java.util.Objects;

public class Money {
    private static final int MINIMUM_AMOUNT = 1_000;
    private static final int MAXIMUM_AMOUNT = 100_000_000;
    static final String INVALID_BETTING_AMOUNT_RANGE_MESSAGE = "배팅 금액은 " + MINIMUM_AMOUNT + "이상 " + MAXIMUM_AMOUNT + "이하여야 합니다. 입력값 :";

    private final int amount;

    private Money(final int amount) {
        this.amount = amount;
    }

    public static Money createMoneyForBetting(final int amount) {
        validateBettingAmountRange(amount);
        return new Money(amount);
    }

    private static void validateBettingAmountRange(final int amount) {
        if (amount < MINIMUM_AMOUNT || amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT_RANGE_MESSAGE + amount);
        }
    }

    public Money multiply(final double payoutRatio) {
        return new Money((int) (this.amount * payoutRatio));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }

    public int getAmount() {
        return amount;
    }
}
