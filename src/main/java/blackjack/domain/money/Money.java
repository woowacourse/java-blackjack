package blackjack.domain.money;

import blackjack.utils.IntegerUtils;

public class Money {

    private final int amount;

    public Money(String input) {
        this(IntegerUtils.parseInt(input));
    }

    public Money(int amount) {
        this.amount = amount;
    }

    public Money multiply(double rate) {
        return new Money((int) (this.amount * rate));
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Money{" +
            "amount=" + amount +
            '}';
    }
}
