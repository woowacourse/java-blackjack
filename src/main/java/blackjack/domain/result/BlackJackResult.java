package blackjack.domain.result;

import blackjack.domain.gamer.BettingMoney;

public enum BlackJackResult {

    BLACKJACK(1.5d),
    WIN(1d),
    DRAW(0d),
    LOSE(-1d);

    private final double profitRate;

    BlackJackResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public BlackJackResult reverse() {
        if (this == BLACKJACK) {
            return LOSE;
        }
        if (this == WIN)
            return LOSE;
        if (this == LOSE)
            return WIN;
        return DRAW;
    }

    public Profit profit(BettingMoney bettingMoney) {
        return Profit.from(bettingMoney.getBettingMoney() * profitRate);
    }

    public double getProfitRate() {
        return profitRate;
    }
}