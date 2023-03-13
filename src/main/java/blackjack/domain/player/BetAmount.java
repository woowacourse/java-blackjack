package blackjack.domain.player;

public class BetAmount {
    private int betAmount;

    public BetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int calculateProfit(int ratio) {
        return this.betAmount * ratio;
    }
}
