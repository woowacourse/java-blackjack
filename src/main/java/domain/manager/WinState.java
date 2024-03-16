package domain.manager;

public enum WinState {

    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double profitMultiplier;

    WinState(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public Profit calculateProfit(Profit profit) {
        return profit.multiply(profitMultiplier);
    }
}
