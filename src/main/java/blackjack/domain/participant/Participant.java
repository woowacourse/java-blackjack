package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {
    protected Name name;
    protected Cards cards;

    protected Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public abstract boolean canDraw();

    public final void receiveCard(Card card) {
        cards.addCard(card);
    }

    public final Cards getCurrentCards() {
        return cards;
    }

    public final Name getName() {
        return name;
    }
}
