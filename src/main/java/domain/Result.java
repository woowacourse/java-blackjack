package domain;

public enum Result {
    승, 무, 패;

    public static Result determinePlayerResult(
            int dealerScore,
            int playerScore,
            boolean isDealerBust,
            boolean isPlayerBust
    ) {
        if (isDealerBust) {
            return Result.승;
        }
        if (isPlayerBust) {
            return Result.패;
        }
        return compareScoreForCheckPlayerResult(dealerScore, playerScore);
    }

    private static Result compareScoreForCheckPlayerResult(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return Result.패;
        }
        if (dealerScore == playerScore) {
            return Result.무;
        }
        return Result.승;
    }

    public Result reverse() {
        if (this.equals(Result.승)) {
            return Result.패;
        }

        if (this.equals(Result.패)) {
            return Result.승;
        }

        return Result.무;
    }
}
