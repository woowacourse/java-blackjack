package blackjack.model.player;

import blackjack.model.card.Card;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cards {

    private final List<Card> values;

    public Cards(Card card1, Card card2, Card... cards) {
        this.values = concat(concat(card1, card2), cards)
            .collect(Collectors.toList());
    }

    public Cards(Cards cards) {
        this.values = List.copyOf(cards.values());
    }

    private Cards(List<Card> cards) {
        this.values = cards;
    }

    private Stream<Card> concat(Card card1, Card card2) {
        return Stream.concat(Stream.of(card1), Stream.of(card2));
    }

    private Stream<Card> concat(Stream<Card> cards1, Card[] cards2) {
        return Stream.concat(cards1, List.of(cards2).stream());
    }

    public Collection<Card> values() {
        return List.copyOf(values);
    }

    public Cards openedCards(int count) {
        return new Cards(values.subList(0, count));
    }

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
