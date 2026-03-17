package domain.bet;

public record Profit(long amount) {

    public static Profit calculate(Money money, double profitRatio) {
        long profit = Math.round(money.amount() * profitRatio);
        return new Profit(profit);
    }

    public Profit plus(Profit profit) {
        return new Profit(this.amount + profit.amount);
    }

    public Profit negate() {
        return new Profit(-amount);
    }
}
