package blackjack.domain;

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
