package domain;

public enum GameResult {
    승, 무, 패;

    public static GameResult determinePlayerResult(
            int dealerScore,
            int playerScore,
            boolean isDealerBust,
            boolean isPlayerBust
    ) {
        if (isDealerBust) {
            return GameResult.승;
        }
        if (isPlayerBust) {
            return GameResult.패;
        }
        return compareScoreForCheckPlayerResult(dealerScore, playerScore);
    }

    private static GameResult compareScoreForCheckPlayerResult(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return GameResult.패;
        }
        if (dealerScore == playerScore) {
            return GameResult.무;
        }
        return GameResult.승;
    }

    public GameResult reverse() {
        if (this.equals(GameResult.승)) {
            return GameResult.패;
        }

        if (this.equals(GameResult.패)) {
            return GameResult.승;
        }

        return GameResult.무;
    }
}
