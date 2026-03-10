package blackjack.model;

public class Score {
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isSame(Score otherScore) {
        return score == otherScore.score;
    }

    public boolean isLess(Score otherScore) {
        return score < otherScore.score;
    }

    // TODO: 매직넘버 제거 (2026. 3. 9.)
    public boolean isDealerHitScore() {
        return score <= 16;
    }

    public boolean isPlayerHitScore() {
        return score < 21;
    }

    public boolean isBurst() {
        return score > 21;
    }

    public int getScore() {
        return score;
    }
}
