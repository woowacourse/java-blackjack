package blackjack.domain.card;

public record Score(
        int score
) implements Comparable<Score> {

    public static final Score MAX = new Score(100);
    public static final Score MIN = new Score(0);
    public static final Score BLACKJACK = new Score(21);

    public static Score min(Score a, Score b) {
        if (a.score > b.score) {
            return b;
        }
        return a;
    }

    public static Score max(Score a, Score b) {
        if (a.score > b.score) {
            return a;
        }
        return b;
    }

    public Score plus(Score score) {
        return new Score(this.score + score.score);
    }

    public boolean isBust() {
        return this.compareTo(BLACKJACK) > 0;
    }

    public boolean isBlackjack() {
        return this.compareTo(BLACKJACK) == 0;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(score, o.score);
    }
}
