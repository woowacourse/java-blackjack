package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.game.Hand;

import java.util.List;

public abstract class Participant {
    protected final Hand cards;
    protected final Name name;

    protected Participant(final Hand cards, final String name) {
        this.cards = cards;
        this.name = new Name(name);
    }

    protected Participant(final Deck deck, final String name) {
        this.cards = new Hand(deck);
        this.name = new Name(name);
    }

    public int getTotalPoint() {
        return cards.getMaxValueNearBlackJack();
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
