package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.Collection;
import java.util.Objects;

public abstract class ScoreCards implements Cards {

    private final Cards cards;

    public ScoreCards(Cards cards) {
        this.cards = cards;
    }

    @Override
    public final void take(Card card) {
        cards.take(card);
    }

    @Override
    public final Collection<Card> values() {
        return cards.values();
    }

    @Override
    public final Cards openedCards(int count) {
        return cards.openedCards(count);
    }

    public final boolean lessThan(Score score) {
        return score().lessThan(score);
    }

    @Override
    public final boolean equals(Object o) {
        return cards.equals(o);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(cards);
    }

    public abstract Score score();
}
