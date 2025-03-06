package domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String title;

    GameResult(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static GameResult from(Score playerScore, Score dealerScore) {
        if (playerScore == Score.BUST || playerScore.isLowerThan(dealerScore)) {
            return LOSE;
        }

        if (playerScore == dealerScore) {
            return DRAW;
        }

        return WIN;
    }
}
