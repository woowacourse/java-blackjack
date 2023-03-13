package blackjack.domain.participants;

public class Money {

    private final double amount;

    public Money(final double amount) {
        this.amount = amount;
    }

    public Money() {
        this(0);
    }

    public Money add(final Money add) {
        return new Money(amount + add.amount);
    }

    public Money multiple(final double profit) {
        return new Money(amount * profit);
    }

    public double getAmount() {
        return amount;
    }

}
