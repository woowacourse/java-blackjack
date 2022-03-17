package blackjack.domain.card.cards;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(Cards copiedCards) {
        this.cards = List.copyOf(copiedCards.cards);
    }

    public void add(final Card card) {
        cards.add(Card.copyOf(card));
    }

    public int size() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public int getRawPoint() {
        return cards.stream()
                .mapToInt(card -> card.getDenomination().getPoint())
                .sum();
    }

    public int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return cards.toString().substring(1, cards.toString().length() - 1);
    }
}
