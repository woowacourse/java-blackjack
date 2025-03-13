package domain;

public enum GameResult {
    WIN("승", 1.0),
    BLACKJACK_WIN("승 (블랙잭)", 1.5),
    LOSE("패", -1.0),
    DRAW("무", 0.0);

    private final String title;
    private final double multiple;

    GameResult(String title, double multiple) {
        this.title = title;
        this.multiple = multiple;
    }

    public static GameResult of(Score playerScore, Score dealerScore) {
        if (playerScore == Score.BUST || playerScore.isLowerThan(dealerScore)) {
            return LOSE;
        }

        if (playerScore == dealerScore) {
            return DRAW;
        }

        if (playerScore == Score.BLACKJACK) {
            return BLACKJACK_WIN;
        }

        return WIN;
    }

    public String getTitle() {
        return title;
    }

    public double getMultiple() {
        return multiple;
    }
}
