package domain.betting;

import domain.game.Result;

public class Profit {
    private final int money;

    private Profit(final int money) {
        this.money = money;
    }

    public static Profit of(Bet bet, Result result) {
        if (result.isPlayerWin()) {
            return new Profit(bet.getMoney());
        }
        if (result.isDealerWin()) {
            return new Profit(bet.getMoney() * -1);
        }
        if (result.isPush()) {
            return new Profit(0);
        }
        return new Profit((int) (bet.getMoney() * 1.5));
    }

    public int getMoney() {
        return money;
    }
}
