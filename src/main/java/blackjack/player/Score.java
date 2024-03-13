package blackjack.player;

public class Score {

    private static final int BLACKJACK = 21;

    private int score;

    public Score(int score) {
        this.score = score;
    }

    public Score add(int score) {
        return add(new Score(score));
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }

    public boolean isBust() {
        return score > BLACKJACK;
    }

    public boolean isNotBust() {
        return !isBust();
    }
}
