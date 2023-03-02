package blackjack.domain;

import java.util.List;

public abstract class Participant {
    protected final ParticipantCards cards;

    protected Participant(final ParticipantCards cards) {
        this.cards = cards;
    }

    protected int getTotalPoint() {
        return cards.calculate();
    }

    protected void hit(final Card card) {
        cards.receive(card);
    }

    protected List<Card> open(final int cardCount) {
        return cards.open(cardCount);
    }

    protected abstract boolean isHittable();
}
