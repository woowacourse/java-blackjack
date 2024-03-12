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
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += findAddScore(totalScore, card);
        }
        return new HandsScore(totalScore);
    }

    private static int findAddScore(int presentScore, Card card) {
        if (card.getValue() == Value.ACE && presentScore + ACE_HIGH_SCORE <= BLACK_JACK) {
            return ACE_HIGH_SCORE;
        }
        return card.getScore();
    }

    public int getScore() {
        return handsScore;
    }

    public boolean isBurst() {
        return handsScore > BLACK_JACK;
    }
}
