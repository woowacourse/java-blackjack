package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class Participant {
    protected final Cards cards;

    public Participant() {
        cards = new Cards();
    }

    public abstract String showCardsAtFirst();

    public abstract String getName();

    public void drawAtFirst(Deck deck) {
        hit(deck.pop());
        hit(deck.pop());
    }

    public void hit(Card card) {
        this.cards.addCard(card);
    }

    public String getCards() {
        return this.cards.getCards();
    }

    public int calculateScore() {
        return cards.calculateScore();
    }
}
