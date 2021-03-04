package blackjack.domain.card;

public enum Result {
    NONE("None"),
    DRAW("무승부"),
    WIN("승리"),
    BLACKJACK_WIN("블랙잭"),
    LOSE("패배");

    private static final int BLACKJACK_VALUE = 21;

    private final String resultName;

    Result(String resultName) {
        this.resultName = resultName;
    }

    public static Result of(boolean isDealerBlackjack, boolean isPlayerBlackjack) {
        if (isDealerBlackjack && !isPlayerBlackjack) {
            return Result.LOSE;
        }

        if (isDealerBlackjack) {
            return Result.DRAW;
        }

        if (isPlayerBlackjack) {
            return Result.BLACKJACK_WIN;
        }

        return Result.NONE;
    }

    public static Result of(int dealerScore, int playerScore) {
        if (isBurst(dealerScore) && !isBurst(playerScore)) {
            return Result.WIN;
        }

        if (dealerScore > playerScore || isBurst(playerScore)) {
            return Result.LOSE;
        }

        if (dealerScore == playerScore) {
            return Result.DRAW;
        }

        return Result.WIN;
    }

    public static boolean isBurst(int score) {
        return score > BLACKJACK_VALUE;
    }

    public String getResultName() {
        return resultName;
    }
}
