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

    public final void receiveCard(Card card) {
        this.cards = cards.addCard(card);
    }

    public final Cards getCurrentCards() {
        return cards;
    }

    public final Name getName() {
        return name;
    }
}
