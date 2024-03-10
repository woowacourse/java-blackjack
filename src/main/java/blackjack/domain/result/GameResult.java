package blackjack.domain.result;

import blackjack.domain.player.Score;

public enum GameResult {
    WIN, LOSE, DRAW;

    public static GameResult calculatePlayerResult(Score playerScore, Score dealerScore) {
        if (playerScore.isBusted()) {
            return LOSE;
        }
        if (dealerScore.isBusted()) {
            return WIN;
        }

        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return LOSE;
        }
        return DRAW;
    }
}
