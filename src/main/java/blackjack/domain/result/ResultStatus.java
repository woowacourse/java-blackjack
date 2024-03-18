package blackjack.domain.result;

import blackjack.domain.player.Profit;

public enum ResultStatus {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private double rate;

    ResultStatus(double rate) {
        this.rate = rate;
    }

    public Profit calculateProfit(int bettingAmount) {
        return new Profit(rate * bettingAmount);
    }
}
