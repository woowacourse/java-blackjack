package domain.bet;

public record Profit(long amount) {

    public static Profit of(long amount) {
        return new Profit(amount);
    }

    public static Profit calculate(Money money, double profitRatio) {
        return new Profit((long) (money.amount() * profitRatio));
    }

    public Profit plus(Profit profit) {
        return new Profit(this.amount + profit.amount);
    }

    public Profit negate() {
        return new Profit(-amount);
    }
}
