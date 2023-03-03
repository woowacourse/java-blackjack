package blackjack.domain;

import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final ParticipantCards cards;

    protected Participant(final ParticipantCards cards, final String name) {
        this.cards = cards;
        this.name = new Name(name);
    }

    protected ResultType compare(final Participant participant) {
        int totalPoint = getTotalPoint();
        int compareTotalPoint = participant.getTotalPoint();
        return ResultType.findBy(totalPoint, compareTotalPoint);
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

    public Name getName() {
        return name;
    }
}
