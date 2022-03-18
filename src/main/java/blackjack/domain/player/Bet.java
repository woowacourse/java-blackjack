package blackjack.domain.player;

import java.util.List;

public final class Bet {

    private static final double WIN = 1.0;
    private static final double LOSE = -1.0;
    private static final double BLACKJACK = 1.5;
    private static final double BOTH_BLACKJACK = 0.0;

    private final int betAmount;
    private int profit;

    public Bet(final int betAmount) {
        this.betAmount = betAmount;
        this.profit = 0;
    }

    public void win() {
        this.profit = (int) (betAmount * WIN);
    }

    public void lose() {
        this.profit = (int) (betAmount * LOSE);
    }

    public void bothBlackjack() {
        this.profit = (int) (betAmount * BOTH_BLACKJACK);
    }

    public void blackjack() {
        this.profit = (int) (betAmount * BLACKJACK);
    }

    public void calculateFinalProfit(final List<Integer> profits) {
        for (Integer profit : profits) {
            this.profit -= profit;
        }
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betAmount=" + betAmount +
                ", profit=" + profit +
                '}';
    }
}
