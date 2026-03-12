package blackjack.domain.betting;

public class Profit {

    private final int amount;

    public Profit(final int amount) {
        this.amount = amount;
    }

    public Profit add(final Profit other) {
        return new Profit(this.amount + other.amount);
    }

    public Profit negate() {
        return new Profit(-amount);
    }

    public int getAmount() {
        return amount;
    }
}
