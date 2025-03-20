package result;

import card.GameScore;

public enum GameResult {
    WIN(1),
    LOSE(-1),
    PUSH(0),
    BLACKJACK(1.5);

    private final double earningRate;

    GameResult(double earningRate) {
        this.earningRate = earningRate;
    }

    public static GameResult determinePlayerResult(final GameScore playerScore, final GameScore dealerScore) {
        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        if (dealerScore.isGreaterThan(playerScore)) {
            return LOSE;
        }
        return PUSH;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
