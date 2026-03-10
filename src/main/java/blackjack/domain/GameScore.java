package blackjack.domain;

public class GameScore {

    public static final GameScore BLACKJACK_SCORE = new GameScore(21);

    private final int score;

    public GameScore(int score) {
        this.score = score;
    }

    public boolean isBiggerThan(GameScore other) {
        return this.score > other.score;
    }

    public boolean isSameOrSmallerThan(GameScore other) {
        return this.score <= other.score;
    }

    public boolean isSame(GameScore other) {
        return this.score == other.score;
    }

    public GameScore minus(int amount) {
        return new GameScore(this.score - amount);
    }

    public boolean isBust() {
        return this.score > BLACKJACK_SCORE.score;
    }

    public boolean isBustWithAce(int aceCount) {
        return isBust() && aceCount > 0;
    }

    public int getScore() {
        return score;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof GameScore gameScore)) {
            return false;
        }

        return score == gameScore.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
