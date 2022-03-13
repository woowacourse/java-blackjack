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
    public void take(Card card) {
        cards.take(card);
    }

    @Override
    public Collection<Card> values() {
        return cards.values();
    }

    @Override
    public Cards openedCards(int count) {
        return cards.openedCards(count);
    }

    public boolean lessThan(Score score) {
        return score().lessThan(score);
    }

    @Override
    public boolean equals(Object o) {
        return cards.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public abstract Score score();
}
