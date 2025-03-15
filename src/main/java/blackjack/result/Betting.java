package blackjack.result;

public class Betting {

    private final Money money;

    private Betting(Money money) {
        this.money = money;
    }

    public static Betting from(int amount) {
        return new Betting(Money.fromBettingAmount(amount));
    }

    public Money applyProfit(GameResult result) {
        return money.applyProfitRate(result.getProfitPercent());
    }
}
