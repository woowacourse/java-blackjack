package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant implements CardReceivable {

    protected final Name name;
    protected final Cards cards;

    protected Participant(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.calculate();
    }

    public Cards drawCard(final Card card) {
        return cards.draw(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public String getNameAsString() {
        return name.asString();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.toList();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (!(object instanceof final Participant that)) return false;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
