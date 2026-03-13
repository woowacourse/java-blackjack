package domain;

public enum Result {
    WIN("승"), TIE("무"), LOSS("패");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public static Result determinePlayerResult(
            boolean isDealerBust,
            boolean isPlayerBust,
            int dealerScore,
            int playerScore
    ) {
        if (isPlayerBust) {
            return Result.LOSS;
        }
        if (isDealerBust) {
            return Result.WIN;
        }
        return comparePlayerScoreWithDealerScore(dealerScore, playerScore);
    }

    private static Result comparePlayerScoreWithDealerScore(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return Result.LOSS;
        }
        if (dealerScore == playerScore) {
            return Result.TIE;
        }
        return Result.WIN;
    }

    public String getName() {
        return name;
    }

    public Result reverse() {
        if (this == Result.WIN) {
            return Result.LOSS;
        }

        if (this == Result.LOSS) {
            return Result.WIN;
        }

        return Result.TIE;
    }
}
