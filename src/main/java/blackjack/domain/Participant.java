package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private static final int TARGET_SCORE = 21;

    protected final CardStorage cardStorage;

    protected Participant() {
        this.cardStorage = new CardDeck();
    }

    abstract boolean canHit();

    public abstract int calculateTotalCardScore();

    public boolean isBust() {
        return calculateTotalCardScore() > TARGET_SCORE;
    }

    public void addCard(Card card) {
        cardStorage.add(card);
    }

    public List<Card> getCardDeck() {
        return cardStorage.getCards();
    }

    public abstract String getName();
}
