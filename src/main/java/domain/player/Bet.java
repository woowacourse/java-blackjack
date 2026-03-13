package domain.player;

import domain.game.Result;

public class Bet {
    private static final double BLACKJACK_PAYOUT_RATE = 1.5;
    private static final int DRAW_PROFIT = 0;

    private final Money money;

    public Bet(Money money) {
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
        return money.multiply(BLACKJACK_PAYOUT_RATE);
    }

    public int loseProfit() {
        return -money.getAmount();
    }
}
