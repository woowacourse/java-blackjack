package blackjack.domain.hand;

public class Score {
    public static final int BLACKJACK_NUMBER = 21;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isBust() {
        return score > BLACKJACK_NUMBER;
    }

    public int getScore() {
        return score;
    }

    public boolean isLess(int score) {
        return this.score < score;
    }

    public boolean isSame(Score score) {
        return this.score == score.score;
    }

    public boolean isGreaterThan(Score score) {
        return this.score > score.score;
    }

    public boolean isBlackjackNumber() {
        return score == BLACKJACK_NUMBER;
    }
}
