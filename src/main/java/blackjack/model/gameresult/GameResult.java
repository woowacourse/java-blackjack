package blackjack.model.gameresult;

import blackjack.model.bet.BetAmount;

public enum GameResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private final double payoutRate;

    GameResult(double payoutRate) {
        this.payoutRate = payoutRate;
    }

    public int calculateProfit(BetAmount betAmount) {
        return (int) Math.round(betAmount.getAmount() * this.payoutRate);
    }
}