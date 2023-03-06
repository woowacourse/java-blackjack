package blackjack.domain;

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

    protected abstract boolean isHittable();

    public List<Card> open(final int cardCount) {
        return cards.open(cardCount);
    }

    public List<Card> openAll() {
        return cards.openAll();
    }

    public Name getName() {
        return name;
    }
}
