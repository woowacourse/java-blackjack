package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Person {
    protected Cards cards;
    protected String name;

    public void receiveCard(Card card) {
        this.cards = cards.addCard(card);
    }

    public Cards getTakenCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canDraw();
}
