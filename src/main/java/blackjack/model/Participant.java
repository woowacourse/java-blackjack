package blackjack.model;

import java.util.List;

public abstract class Participant {
    protected final Cards cards;

    protected Participant() {
        this.cards = new Cards();
    }

    public abstract boolean checkDrawCardState();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public void addCards(List<Card> cardToAdd) {
        cards.addCard(cardToAdd);
    }

    public Cards getCards() {
        return cards;
    }
}
