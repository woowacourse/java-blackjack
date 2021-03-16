package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class Participant {
    protected final Cards cards;

    public Participant() {
        cards = new Cards();
    }

    protected abstract String showCardsAtFirst();

    protected abstract String getName();

    public void drawAtFirst(Deck deck) {
        hit(deck.pop());
        hit(deck.pop());
    }

    public void hit(Card card) {
        this.cards.addCard(card);
    }

    public String getCardsInformation() {
        return this.cards.getCardsInformation();
    }

    protected boolean isNotBlackJack() {
        return !isBlackJack();
    }

    protected boolean isBlackJack() {
        return (cards.calculateScore() == Cards.BLACKJACK_SCORE) && (cards.size() == 2);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }
}
