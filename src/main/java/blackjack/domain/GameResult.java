package blackjack.domain;

public enum GameResult {
    WIN, LOSE, DRAW;

    // TODO: 현재 같은 타입 인자가 2개라 혼동의 여지가 있음.
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

    public boolean isWin() {
        return this.equals(WIN);
    }

    public boolean isLose() {
        return this.equals(LOSE);
    }
    public boolean isDraw() {
        return this.equals(DRAW);
    }
}
