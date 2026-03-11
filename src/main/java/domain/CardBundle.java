package domain;

import java.util.ArrayList;
import java.util.List;

public class CardBundle {
    private final List<Card> cards;
    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_BONUS = 10;

    public CardBundle() {
        this.cards = new ArrayList<>();
    }

    public int calculateScore() {
        int score = 0;
        boolean hasAce = false;

        for (Card card : cards) {
            score += card.getScore();
            if (card.isAce()) {
                hasAce = true;
            }
        }
        return applyAceBonus(score, hasAce);
    }

    private int applyAceBonus(int score, boolean hasAce) {
        if (isSoftHand(score, hasAce)) {
            return score + 10;
        }
        return score;
    }

    private boolean isSoftHand(int score, boolean hasAce) {
        return hasAce && score + ACE_BONUS <= BUST_THRESHOLD;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
