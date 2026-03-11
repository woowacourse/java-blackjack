package blackjack.domain;

public class Score {
    public static final int BLACKJACK_NUMBER = 21;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isBurst() {
        return score > BLACKJACK_NUMBER;
    }

    public int getScore() {
        return score;
    }

    public boolean isLess(int score) {
        return this.score < score;
    }

    public boolean isBlackjack() {
        return score == BLACKJACK_NUMBER;
    }
}
