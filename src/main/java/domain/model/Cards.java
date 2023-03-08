package domain.model;

import domain.type.Letter;
import java.util.HashSet;
import java.util.Set;

public class Cards {

    private final Set<Card> cards;

    public Cards(final Set<Card> cards) {
        this.cards = cards;
    }

    public static Cards makeEmpty() {
        return new Cards(new HashSet<>());
    }

    public int count(final Letter letter) {
        return (int) cards.stream()
            .filter(card -> card.isMatch(letter))
            .count();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isNotEmpty() {
        return !cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public Set<Card> getCards() {
        return Set.copyOf(cards);
    }
}
