package domain.cards;

import java.util.List;

public class Score {

    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int A_SCORE_GAP = 10;

    private int value;

    public Score(int value) {
        this.value = value;
    }

    public Score() {
        this(0);
    }

    public void sumAllCards(List<Card> cards) {
        value = cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public void decideScore() {
        if (cannotBust(value + A_SCORE_GAP)) {
            value = value + A_SCORE_GAP;
        }
    }

    public boolean cannotBust(int score) {
        return score <= BUST_THRESHOLD;
    }

    public boolean cannotBust() {
        return value <= BUST_THRESHOLD;
    }

    public boolean cannotDealerHit() {
        return value <= DEALER_HIT_THRESHOLD;
    }

    public boolean hasScore(int score) {
        return value == score;
    }

    public int compareScore(Score score) {
        return value - score.value;
    }

    public int getValue() {
        return value;
    }
}
