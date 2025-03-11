package blackjack.domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static GameResult checkDealerWin(final Score playerScore, final Score dealerScore) {

        if (playerScore.isBust()) {
            return GameResult.WIN;
        }
        if (dealerScore.isBust()) {
            return GameResult.LOSE;
        }

        if (dealerScore.isHigherThan(playerScore)) {
            return GameResult.WIN;
        }
        if (playerScore.isHigherThan(dealerScore)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public static GameResult checkPlayerWin(final Score score, final Score dealer) {
        GameResult gameResult = checkDealerWin(score, dealer);

        if (gameResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public String getStatus() {
        return status;
    }
}
