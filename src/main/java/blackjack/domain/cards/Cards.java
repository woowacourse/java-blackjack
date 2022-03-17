package blackjack.domain.cards;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
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

    public int getDenominationCount(Denomination denomination) {
        return (int) cards.stream()
                .filter(card -> card.isSameDenomination(denomination))
                .count();
    }

    public List<Card> get() {
        return List.copyOf(cards);
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
}
