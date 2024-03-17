package blackjack.domain.participant;

public class Money {

    private final double amount;

    public Money(double amount) {
        this.amount = amount;
    }

    public Money multiply(double multiplier) {
        return new Money(amount * multiplier);
    }

    public boolean isGreaterEqualThan(double target) {
        return amount >= target;
    }

    public double getAmount() {
        return amount;
    }
}
