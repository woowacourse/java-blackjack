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

    public Money add(Money money) {
        if (money == null) {
            return this;
        }
        return new Money(this.amount + money.amount);
    }

    public Money reverse() {
        return new Money(-this.amount);
    }
}
