package blackjack.domain;

public class Money {

    private static final int MIN_BETTING_MONEY = 1;
    private static final int MAX_BETTING_MONEY = 100_000_000;

    private final int value;

    private Money(final int value) {
        this.value = value;
    }

    public static Money forBetting(final int value) {
        validateBettingMoney(value);
        return new Money(value);
    }

    public static Money dealer() {
        return new Money(0);
    }

    private static void validateBettingMoney(final int value) {
        if (value < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException("베팅 금액은 1 이상이여야 합니다.");
        }
        if (value > MAX_BETTING_MONEY) {
            throw new IllegalArgumentException("베팅 금액은 100,000,000 이하여야 합니다.");
        }
    }

    public Money calculateProfit(final double profitRate) {
        return new Money((int) (this.value * profitRate));
    }

    public Money minus(final Money money) {
        return new Money(this.value - money.getValue());
    }

    public int getValue() {
        return this.value;
    }
}
