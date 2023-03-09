package domain.game;

public class BettingMoney {
    private final int money;

    private BettingMoney(final int money) {
        this.money = money;
    }

    public static BettingMoney create(final int money) {
        return new BettingMoney(money);
    }
}
