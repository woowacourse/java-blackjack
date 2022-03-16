package blackjack.domain.player;

import java.util.List;

public final class Bet {

    private static final double WIN = 1.0;
    private static final double LOSE = -1.0;
    private static final double BLACKJACK = 1.5;
    private static final double BOTH_BLACKJACK = 0.0;


    private final int betAmount;
    private int profit;

    public Bet(int betAmount) {
        this.betAmount = betAmount;
        this.profit = 0;
    }

    public void win() {
        this.profit = betAmount * (int) WIN;;
    }

    public void lose() {
        this.profit = betAmount * (int) LOSE;
    }

    public void bothBlackjack() {
        this.profit = betAmount * (int) BOTH_BLACKJACK;
    }

    public void blackjack() {
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
