package domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private static final int MIN_BUST_NUMBER = 21;
    private static final int ZERO_SCORE = 0;
    private static final int INIT_CARD_SIZE = 2;
    private static final int PLUS_ACE_COUNT = 10;

    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int scoreSum = ZERO_SCORE;
        for (Card card : cards) {
            scoreSum += card.getValue();
        }
        return getAceCase(aceCheck(), scoreSum);
    }

    private boolean aceCheck() {
        boolean hasAce = false;
        for (Card card : cards) {
            hasAce = hasAce || card.isAce();
        }
        return hasAce;
    }

    private int getAceCase(boolean hasAce, int scoreSum) {
        if (hasAce && scoreSum + PLUS_ACE_COUNT <= MIN_BUST_NUMBER) {
            return scoreSum + PLUS_ACE_COUNT;
        }
        return scoreSum;
    }

    public boolean checkBust() {
        return calculateScore() > MIN_BUST_NUMBER;
    }

    public boolean isBlackJack() {
        return calculateScore() == MIN_BUST_NUMBER && cards.size() == INIT_CARD_SIZE;
    }

    public List<Card> getCards() {
        return cards;
    }
}
