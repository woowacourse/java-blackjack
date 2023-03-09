package blackjack.domain.game;

import java.util.Objects;

public class BetMoney {
    static final String INVALID_ACCOUNT_RANGE_MESSAGE = "배팅 금액은 1,000 이상 100,000,000 이하여야 합니다. 입력값 :";
    private static final int MINIMUM_AMOUNT = 1_000;
    private static final int MAXIMUM_AMOUNT = 100_000_000;

    private final int amount;

    private BetMoney(final int amount) {
        this.amount = amount;
    }

    public static BetMoney createBetMoney(final int amount) {
        validateAmountRange(amount);
        return new BetMoney(amount);
    }

    private static void validateAmountRange(final int amount) {
        if (amount < MINIMUM_AMOUNT || amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_RANGE_MESSAGE + amount);
        }
    }

    public BetMoney winMoney(final boolean isBlackjack) {
        if (isBlackjack) {
            return new BetMoney((int) (amount * 2.5));
        }
        return new BetMoney(amount * 2);
    }

    public BetMoney loseMoney() {
        return this.subtract(this);
    }

    public BetMoney subtract(final BetMoney betMoney) {
        return new BetMoney(this.amount - betMoney.amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BetMoney betMoney = (BetMoney) o;
        return amount == betMoney.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "BetMoney{" +
                "amount=" + amount +
                '}';
    }

    public int getAmount() {
        return amount;
    }
}
