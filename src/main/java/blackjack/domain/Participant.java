package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private static final int TARGET_SCORE = 21;

    protected final CardDeck cardDeck;

    public Participant(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    abstract boolean canHit();

    public abstract int calculateTotalCardScore();

    public boolean isBust() {
        return calculateTotalCardScore() > TARGET_SCORE;
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }

    public abstract String getName();
}
