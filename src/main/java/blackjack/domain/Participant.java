package blackjack.domain;

public abstract class Participant {
    protected final ParticipantCards cards;

    protected Participant(final ParticipantCards cards) {
        this.cards = cards;
    }

    int getTotalPoint() {
        return cards.calculate();
    }
}
