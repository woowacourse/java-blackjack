package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Person {
    protected Name name;
    protected Cards cards;

    protected Person(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public abstract boolean canDraw();

    public void receiveCard(Card card) {
        this.cards = cards.addCard(card);
    }

    public Cards getCurrentCards() {
        return cards;
    }

    public Name getName() {
        return name;
    }
}
