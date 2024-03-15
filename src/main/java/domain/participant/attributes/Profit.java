package domain.participant.attributes;

public record Profit(int amount) implements Money {

    public Profit add(final Money money) {
        return new Profit(this.amount + money.amount());
    }

    public Profit subtract(final Money money) {
        return new Profit(this.amount - money.amount());
    }

    public Profit multiply(final double multiplier) {
        return new Profit((int) (amount * multiplier));
    }
}
