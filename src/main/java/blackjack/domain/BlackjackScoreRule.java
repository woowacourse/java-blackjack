package blackjack.domain;

public enum BlackjackScoreRule {

    BLACKJACK_SCORE(21),
    ENABLE_MAXIMUM_SCORE_UNDER_BUST(21),
    DEALER_ENABLE_MINIMUM_SCORE(17);

    private final int score;

    BlackjackScoreRule(final int score) {
        this.score = score;
    }

    public boolean isEquals(final int score) {
        return this.score == score;
    }

    public boolean isNotEquals(final int score) {
        return !isEquals(score);
    }

    public boolean isOverThan(final int score) {
        return this.score > score;
    }

    public boolean isNotOverThan(final int score) {
        return !isOverThan(score);
    }

    public boolean isUnderThan(final int score) {
        return this.score < score;
    }

    public boolean isNotUnderThan(final int score) {
        return !isUnderThan(score);
    }

}
