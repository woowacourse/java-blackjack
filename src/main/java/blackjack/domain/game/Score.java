package blackjack.domain.game;

import blackjack.domain.card.CardRank;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int ACE_SCORE_DIFFERENCE = CardRank.ACE.getSpecialScore() - CardRank.ACE.getScore();

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score add(Score other) {
        return new Score(this.value + other.value);
    }

    public boolean isBlackjack() {
        return this.value == BLACKJACK;
    }

    public boolean isBust() {
        return value > BLACKJACK;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isPlayerHit() {
        return isNotBust();
    }

    public boolean isDealerHit() {
        return this.value <= DEALER_HIT_THRESHOLD;
    }

    public boolean canHit(Score threshold) {
        return value <= threshold.value;
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

    public int getValue() {
        return value;
    }
}
