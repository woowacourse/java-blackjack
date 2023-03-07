package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.ParticipantCards;

import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final ParticipantCards cards;

    protected Participant(final ParticipantCards cards, final String name) {
        this.cards = cards;
        this.name = new Name(name);
    }

    public int getTotalPoint() {
        return cards.calculate();
    }

    public List<Card> open(final int cardCount) {
        return cards.open(cardCount);
    }

    public List<Card> openAll() {
        return cards.openAll();
    }

    public Name getName() {
        return name;
    }

    public void hit(final Card card) {
        cards.receive(card);
    }

    public abstract boolean isHittable();
}
