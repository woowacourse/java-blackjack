package domain.game;

public class BetAmount {
    private final int betAmount;

    public BetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int calculateProfit(double ratio) {
        return (int)(betAmount * ratio);
    }
}
