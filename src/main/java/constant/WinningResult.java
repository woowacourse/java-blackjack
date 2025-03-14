package constant;

public enum WinningResult {
    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private static final int BUST_STANDARD = 21;

    private final double profitRate;

    WinningResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static WinningResult getWinningResult(int playerScore, int dealerScore) {
        if (playerScore > BUST_STANDARD || dealerScore > BUST_STANDARD) {
            return getWinningResultOverBustStandard(playerScore);
        }
        return determineWinningResult(playerScore, dealerScore);
    }

    private static WinningResult getWinningResultOverBustStandard(int playerScore) {
        if (playerScore > BUST_STANDARD) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    private static WinningResult determineWinningResult(int playerScore, int dealerScore) {
        if (playerScore == dealerScore) {
            return WinningResult.DRAW;
        }
        if (playerScore > dealerScore) {
            return WinningResult.WIN;
        }
        return WinningResult.LOSE;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
