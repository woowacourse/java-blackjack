package blackjack.domain.player;

import java.util.List;

public final class Bet {

    private final int betAmount;
    private int profit;

    public Bet(final int betAmount) {
        this.betAmount = betAmount;
        this.profit = 0;
    }

    public void win() {
        this.profit = (int) (betAmount * BetPoint.WIN.getRatio());
    }

    public void lose() {
        this.profit = (int) (betAmount * BetPoint.LOSE.getRatio());
    }

    public void bothBlackjack() {
        this.profit = (int) (betAmount * BetPoint.BOTH_BLACKJACK.getRatio());
    }

    public void blackjack() {
        this.profit = (int) (betAmount * BetPoint.BLACKJACK.getRatio());
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
