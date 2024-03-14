package blackjack.money;

public class Money {

    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final int amount;

    protected Money(int amount) {
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    public static Money zero() {
        return new Money(0);
    }

    public Money add(Money money) {
        return Money.of(amount + money.amount);
    }

    public Money profitOnTie() {
        return zero();
    }

    public Money profitOnBlackJackWin() {
        return this.multipliedBy(BLACKJACK_PROFIT_RATE);
    }

    private Money multipliedBy(double rate) {
        return Money.of((int) (amount * rate));
    }

    public Money profitOnWin() {
        return Money.of(amount);
    }

    public Money profitOnLose() {
        return this.negate();
    }

    private Money negate() {
        return new Money(-amount);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
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
        return amount;
    }
}
