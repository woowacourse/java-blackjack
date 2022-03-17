package blackjack.domain.money;

public class Money {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money from(int betted) {
        return new Money(betted);
    }

    public static Money createBlackjackProfit(Money money) {
        int blackjackProfitValue = (int) Math.round(money.value * BLACKJACK_PROFIT_RATE);
        return new Money(blackjackProfitValue);
    }

    public static Money createAsNegative(Money money) {
        return new Money(-money.value);
    }

    public boolean isNegative() {
        return value < 0;
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Money money = (Money) o;

        return value == money.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
