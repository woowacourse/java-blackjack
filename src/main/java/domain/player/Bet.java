package domain.player;

public class Bet {

    private static final double BLACKJACK_PAYOUT_RATE = 1.5;
    private static final int DRAW_MONEY = 0;

    private final Money money;

    public Bet(Money money) {
        this.money = money;
    }

    public int winProfit() {
        return money.getAmount();
    }

    public int refundProfit() {
        return DRAW_MONEY;
    }

    public int blackjackWinProfit() {
        return money.multiply(BLACKJACK_PAYOUT_RATE);
    }

    public int loseProfit() {
        return -money.getAmount();
    }
}
