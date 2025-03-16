package blackjack.result;

public class Betting {

    private final Money money;

    private Betting(Money money) {
        this.money = money;
    }

    public static Betting from(int amount) {
        validateNegative(amount);
        return new Betting(Money.from(amount));
    }

    private static void validateNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수가 될 수 없습니다.");
        }
    }

    public Money applyProfit(GameResult result) {
        return money.applyProfitRate(result.getProfitPercent());
    }
}
