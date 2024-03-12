package blackjack.domain.rule;

import blackjack.domain.card.CardRank;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int ACE_SCORE_DIFFERENCE =
            CardRank.ACE.getSpecialScore().value - CardRank.ACE.getScore().value;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score add(Score other) {
        return new Score(this.value + other.value);
    }

    public boolean canHit(Score threshold) {
        return value <= threshold.value;
    }

    public boolean isBust() {
        return value > BLACKJACK;
    }

    private boolean isNotBust() {
        return !isBust();
    }

    public boolean isBiggerThan(Score other) {
        return this.value > other.value;
    }

    public Score adjustAceScore() {
        Score newScore = new Score(this.value + ACE_SCORE_DIFFERENCE);
        if (newScore.isNotBust()) {
            return newScore;
        }
        return this;
    }
}
