package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final ParticipantCards cards;

    protected Participant(final ParticipantCards cards, final String name) {
        this.cards = cards;
        this.name = new Name(name);
    }

    protected int getTotalPoint() {
        return cards.calculate();
    }

    protected void hit(final Card card) {
        cards.receive(card);
    }

    public List<Card> openAll() {
        return cards.openAll();
    }

    public Name getName() {
        return name;
    }

    public abstract List<Card> initialOpen();

    protected abstract boolean isHittable();
}
