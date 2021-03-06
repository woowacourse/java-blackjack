package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class Participant {
    private Cards cards;

    public void drawAtFirst(Deck deck) {
        hit(deck.pop());
        hit(deck.pop());
    }

    public void hit(Card card) {
        this.cards.addCard(card);
    }

    String getCards() {
        return this.cards.getCards();
    }

    int getScore() {
        return cards.getScore();
    }

    abstract String showCardsAtFirst();

    abstract String getName();
}
