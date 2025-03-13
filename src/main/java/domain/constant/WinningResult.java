package domain.constant;

public enum WinningResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private static final int BUST_STANDARD = 21;

    private final String message;

    WinningResult(String message) {
        this.message = message;
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

    public String getMessage() {
        return message;
    }
}
