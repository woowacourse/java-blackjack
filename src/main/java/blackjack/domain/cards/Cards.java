package blackjack.domain.cards;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Cards {
    private final List<Card> value;

    public Cards() {
        this.value = new ArrayList<>();
    }

    public Cards(List<Card> value) {
        this.value = value;
    }

    public void add(final Card card) {
        value.add(card);
    }

    public int size() {
        return value.size();
    }

    public Card getFirstCard() {
        return value.get(0);
    }

    public int getRawPoint() {
        return value.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    public int getDenominationCount(Denomination denomination) {
        return (int) value.stream()
                .filter(card -> card.isSameDenomination(denomination))
                .count();
    }

    public List<Card> getCopy() {
        return List.copyOf(value);
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
        return Objects.equals(value, cards1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
