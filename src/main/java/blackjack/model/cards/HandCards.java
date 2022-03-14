package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class HandCards implements TakableCards {

    private final List<Card> values;

    HandCards(Card card1, Card card2, Card... cards) {
        this.values = concat(concat(card1, card2), cards)
            .collect(Collectors.toList());
    }

    private HandCards(List<Card> cards) {
        this.values = cards;
    }

    private Stream<Card> concat(Card card1, Card card2) {
        return Stream.concat(Stream.of(card1), Stream.of(card2));
    }

    private Stream<Card> concat(Stream<Card> cards1, Card[] cards2) {
        return Stream.concat(cards1, List.of(cards2).stream());
    }

    @Override
    public Collection<Card> values() {
        return List.copyOf(values);
    }

    @Override
    public Cards openedCards(int count) {
        return new HandCards(values.subList(0, count));
    }

    @Override
    public void take(Card card) {
        values.add(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Cards)) {
            return false;
        }
        Cards cards = (Cards) o;
        return Objects.equals(values(), cards.values());
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
