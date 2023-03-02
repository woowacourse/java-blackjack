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

    protected abstract void hit(final Card card);

    protected abstract List<Card> open(final int cardCount);

    protected abstract boolean isHittable();
}
