package blackjack.domain.card;

import blackjack.domain.machine.Score;
import java.util.LinkedHashSet;
import java.util.Set;

public class Cards {

    private final Set<Card> values = new LinkedHashSet<>();

    public void addCard(Card card) {
        values.add(card);
    }

    public Set<Card> getCards() {
        return values;
    }

    public Score score() {
        return new Score(values);
    }

    public boolean isOverLimit(int limit) {
        return score().isOverLimit(limit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cards cards = (Cards) o;

        return values != null ? values.equals(cards.values) : cards.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}
