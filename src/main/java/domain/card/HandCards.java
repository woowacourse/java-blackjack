package domain.card;

import static domain.participant.Participant.MIN_BUST_NUMBER;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int scoreSum = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            scoreSum += card.getValue();
            hasAce = hasAce || card.isAce();
        }
        return getAceCase(hasAce, scoreSum);
    }

    private int getAceCase(boolean hasAce, int scoreSum) {
        if (hasAce && scoreSum + 10 <= 21) {
            return scoreSum + 10;
        }
        return scoreSum;
    }

    public int checkBust() {
        int maxSum = calculateScore();
        if (maxSum > MIN_BUST_NUMBER) {
            return 0;
        }
        return maxSum;
    }

    public List<Card> getCards() {
        return cards;
    }
}
