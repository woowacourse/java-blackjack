package blackjack.domain.card;

import blackjack.domain.machine.Score;
import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {

    private final Set<Card> cards = new LinkedHashSet<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public Set<Card> getCards() {
        return cards;
    }

    public Score score() {
        return new Score(cards);
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

        Deck deck = (Deck) o;

        return cards != null ? cards.equals(deck.cards) : deck.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
