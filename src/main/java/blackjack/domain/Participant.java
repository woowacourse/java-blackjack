package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    protected Participant(final Cards cards, final String name) {
        this.cards = cards;
        this.name = new Name(name);
    }

    public int getTotalPoint() {
        return cards.calculate();
    }

    protected void hit(final Card card) {
        cards.receive(card);
    }

    public List<Card> openAll() {
        return cards.open(cards.getSize());
    }

    protected boolean isBlackJack() {
        return cards.isBlackJack();
    }

    protected boolean isBust() {
        return cards.isBust();
    }

    public abstract List<Card> initialOpen();

    protected abstract boolean isHittable();

    public String getName() {
        return name.getValue();
    }
}
