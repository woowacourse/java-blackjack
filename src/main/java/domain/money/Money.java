package domain.money;

public class Money {

    protected final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public Money multiply(double factor) {
        return new Money(Math.round(amount * factor));
    }
}
