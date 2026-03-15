package domain.player;

import domain.game.Result;

public class BettingProfit {
    private static final int DRAW_PROFIT = 0;

    private final Money money;

    public BettingProfit(Money money) {
        this.money = money;
    }

    public int profit(Result result) {
        if (result == Result.WIN) {
            return winProfit();
        }
        if (result == Result.DRAW) {
            return refundProfit();
        }
        return loseProfit();
    }

    public int winProfit() {
        return money.getAmount();
    }

    public int refundProfit() {
        return DRAW_PROFIT;
    }

    public int blackjackWinProfit() {
        return money.multiply(3) / 2;
    }

    public int loseProfit() {
        return -money.getAmount();
    }
}
