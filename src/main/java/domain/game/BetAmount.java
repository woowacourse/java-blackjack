package domain.game;

public final class BetAmount {
    private final int betAmount;

    public BetAmount(final int betAmount) {
        this.betAmount = betAmount;
    }

    public int calculateProfit(final double ratio) {
        return (int)(betAmount * ratio);
    }
}
