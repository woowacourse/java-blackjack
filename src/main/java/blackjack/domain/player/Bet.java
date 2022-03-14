package blackjack.domain.player;

import java.util.List;

public class Bet {

    private final int betAmount;
    private int profit;

    public Bet(int betAmount) {
        this.betAmount = betAmount;
        this.profit = 0;
    }

    public void win() {
        this.profit = (int) (betAmount * 0.5);
    }

    public void lose() {
        this.profit = betAmount * -1;
    }

    public void calculatefinalProfit(List<Participant> participants){
        int finalProfit = 0;
        for (Participant participant : participants) {
            finalProfit -= participant.getBetProfit();
        }
        this.profit = finalProfit;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public int getProfit() {
        return profit;
    }
}
