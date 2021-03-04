package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Person {
    protected Cards cards;
    protected String name;

    public abstract boolean canDraw();

    public void receiveCard(Card card) {
        this.cards = cards.addCard(card);
    }

    public Cards getCurrentCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}
