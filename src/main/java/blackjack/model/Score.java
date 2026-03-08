package blackjack.model;

public class Score {
    private final int score;

    public Score() {
        this.score = 0;
    }

    public Score(int score) {
        this.score = score;
    }

    public boolean isSame(Score otherScore) {
        return score == otherScore.score;
    }

    public boolean isLess(Score otherScore) {
        return score < otherScore.score;
    }

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
