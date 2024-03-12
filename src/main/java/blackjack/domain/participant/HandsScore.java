package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Value;
import java.util.List;

public class HandsScore {
    private static final int BLACK_JACK = 21;
    private static final int ACE_HIGH_SCORE = 11;

    private final int handsScore;

    private HandsScore(int score) {
        this.handsScore = score;
    }

    public static HandsScore of(List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            if (card.getValue() == Value.ACE && score + ACE_HIGH_SCORE <= BLACK_JACK) {
                score += ACE_HIGH_SCORE;
            } else {
                score += card.getScore();
            }
        }
        return new HandsScore(score);
    }

    public int getScore() {
        return handsScore;
    }

    public boolean isBurst() {
        return handsScore > BLACK_JACK;
    }
}
