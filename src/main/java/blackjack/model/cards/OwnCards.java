package blackjack.model.cards;

import blackjack.model.Card;
import blackjack.model.Rank;
import blackjack.model.Score;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class OwnCards implements Cards{

    private final List<Card> cards;

    OwnCards(Card card) {
        this.cards = List.of(card);
    }

    OwnCards(Card card1, Card card2, Card... cards) {
        this.cards = concat(concat(card1, card2), cards)
            .collect(Collectors.toList());
    }

    private Stream<Card> concat(Card card1, Card card2) {
        return Stream.concat(Stream.of(card1), Stream.of(card2));
    }

    private Stream<Card> concat(Stream<Card> cards1, Card[] cards2) {
        return Stream.concat(cards1, List.of(cards2).stream());
    }

    @Override
    public Stream<Card> stream() {
        return cards.stream();
    }

    @Override
    public void take(Card card) {
        cards.add(card);
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
