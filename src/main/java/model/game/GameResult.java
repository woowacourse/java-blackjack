package model.game;

import model.betting.Bet;

public enum GameResult {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    PUSH(1.0),
    LOOSE(-1.0);

    private final double profitRate;

    GameResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public int calculateProfit(Bet bet) {
        return (int) (bet.getAmount() * profitRate);
    }
}
