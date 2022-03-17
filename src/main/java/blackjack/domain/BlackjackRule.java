package blackjack.domain;

public enum BlackjackRule {

    BLACKJACK_SCORE(21),
    DEALER_ENABLE_MINIMUM_SCORE(17);

    private final int score;

    BlackjackRule(final int score) {
        this.score = score;
    }

    public boolean equalsScore(final int score) {
        return this.score == score;
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
