package blackjack.domain.card;

public class Score {

    private static final int BLACKJACK_SCORE = 21;
    private static final int MIN_SCORE = 5;

    private final int score;

    public Score(final int score) {
        checkMinScore(score);
        this.score = score;
    }

    private void checkMinScore(final int score) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("4이하의 점수는 존재하지 않습니다.");
        }
    }

    public boolean isBlackjackScore() {
        return score == BLACKJACK_SCORE;
    }
}
