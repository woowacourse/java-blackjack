package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Human {

    private final Cards cards;

    public Human() {
        this.cards = Cards.create();
    }

    public abstract String getName();

    public abstract boolean isOneMoreCard();

    public void addCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }

    public int getPoint() {
        return getCards().getPoint();
    }

    public boolean isOverThanMaxPoint() {
        return getCards().isOverThanMaxPoint();
    }
}
