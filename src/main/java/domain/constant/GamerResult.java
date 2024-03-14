package domain.constant;

public enum GamerResult {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private static final int BUST_THRESHOLD = 21;
    private final int profitRatio;

    GamerResult(int profitRatio) {
        this.profitRatio = profitRatio;
    }

    public static GamerResult judgeDealerResult(int dealerScore, int playerScore) {
        if (playerScore > BUST_THRESHOLD || (dealerScore <= BUST_THRESHOLD && dealerScore > playerScore)) {
            return GamerResult.WIN;
        }
        if (dealerScore > BUST_THRESHOLD || dealerScore < playerScore) {
            return GamerResult.LOSE;
        }
        return GamerResult.DRAW;
    }

    public GamerResult getOpponentGameResult() {
        if (this.equals(GamerResult.WIN)) {
            return GamerResult.LOSE;
        }
        if (this.equals(GamerResult.LOSE)) {
            return GamerResult.WIN;
        }
        return GamerResult.DRAW;
    }

    public int getProfitRatio() {
        return profitRatio;
    }
}
