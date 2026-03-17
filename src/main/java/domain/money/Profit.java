package domain.money;

public record Profit(Money money) {
    public static BettingMoney from(long amount) {
        return new BettingMoney(new Money(amount));
    }

    public static BettingMoney from(Money money) {
        return new BettingMoney(money);
    }
}
