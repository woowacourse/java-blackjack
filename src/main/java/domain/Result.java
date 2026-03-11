package domain;

public enum Result {
    WIN("승"), TIE("무"), LOSS("패");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public static Result determinePlayerResult(
            int dealerScore,
            int playerScore,
            boolean isDealerBust,
            boolean isPlayerBust
    ) {
        if (isDealerBust) {
            return Result.WIN;
        }
        if (isPlayerBust) {
            return Result.LOSS;
        }
        return compareScoreForCheckPlayerResult(dealerScore, playerScore);
    }

    private static Result compareScoreForCheckPlayerResult(int dealerScore, int playerScore) {
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
        if (this.equals(Result.WIN)) {
            return Result.LOSS;
        }

        if (this.equals(Result.LOSS)) {
            return Result.WIN;
        }

        return Result.TIE;
    }
}
