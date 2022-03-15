package blackjack.domain.player;

import java.util.List;

public final class Bet {

    public static final double WIN = 1.5;
    public static final double LOSE = -1.0;

    private final int betAmount;
    private int profit;

    public Bet(int betAmount) {
        this.betAmount = betAmount;
        this.profit = 0;
    }

    public void win() {
        this.profit = (int) (betAmount * WIN) - betAmount;
    }

    public void lose() {
        this.profit = betAmount * (int) LOSE;
    }

    public void calculateFinalProfit(List<Integer> profits){
        for (Integer profit : profits) {
            this.profit -= profit;
        }
    }

    public int getProfit() {
        return profit;
    }
}
