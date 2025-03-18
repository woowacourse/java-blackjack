package blackjack.domain.card;

public enum WinningResult {
    DRAW(0), BLACKJACK_WIN(1.5), WIN(1), LOSE(-1);

    private double profit;

    WinningResult(double profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }
}
