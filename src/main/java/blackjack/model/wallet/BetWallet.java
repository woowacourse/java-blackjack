package blackjack.model.wallet;

public abstract class BetWallet {

    protected final int betAmount;
    protected int profitAmount;

    protected BetWallet(int betAmount) {
        this.betAmount = betAmount;
        this.profitAmount = 0;
    }

    public int calculateNetProfit() {
        return profitAmount - betAmount;
    }

    public int getProfitAmount() {
        return profitAmount;
    }
}
