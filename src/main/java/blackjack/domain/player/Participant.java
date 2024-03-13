package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant implements CardReceivable {
    private static final int BUST_SIZE = 21;
    private static final Score CHANGE_A_VALUE = Score.from(10);

    protected final Name name;
    protected final Cards cards;

    protected Participant(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public Score calculateScore() {
        return Score.from(cards.calculate());
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.calculate() > BUST_SIZE;
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
