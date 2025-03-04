package domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CardHand {
    private final Set<Card> cards;

    public CardHand(Set<Card> cards) {
        this.cards = new HashSet<>(cards);
    }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CardHand cardHand = (CardHand) o;
        return Objects.equals(cards, cardHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
