package blackjack.domain;

public class Money {

    private final double amount;

    public Money(double amount) {
        this.amount = amount;
    }

    public Money add(Money other) {
        return new Money(amount + other.amount);
    }

    public Money minus(Money other) {
        return new Money(amount - other.amount);
    }

    public Money multiply(double multiplier) {
        return new Money(amount * multiplier);
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public double getAmount() {
        return amount;
    }
}
