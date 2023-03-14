package blackjack.domain.game;

import blackjack.domain.vo.Score;

public enum ScoreState {
    STAY(Score.of(17)),
    BUST(Score.of(22)),
    HIT(Score.of(16)),
    BLACKJACK(Score.of(21)),
    ;

    private final Score score;

    ScoreState(Score score) {
        this.score = score;
    }

    public static ScoreState of(Score score) {
        if (score.equals(BLACKJACK.score)) {
            return BLACKJACK;
        }
        if (score.isGreaterOrEqualsTo(BUST.score)) {
            return BUST;
        }
        if (score.isGreaterOrEqualsTo(STAY.score)) {
            return STAY;
        }
        return HIT;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isNotBust() {
        return this != BUST;
    }

    public boolean isHit() {
        return this == HIT;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }
}
