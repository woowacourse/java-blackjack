package blackjack.model;

public class Money {
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public Money multipliedBy(double rate) {
        int multipliedAmount = (int) Math.round(this.amount * rate);
        return new Money(multipliedAmount);
    }
}
