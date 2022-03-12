package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class HandCards implements Cards{

    private final List<Card> cards;

    HandCards(Card card1, Card card2, Card... cards) {
        this.cards = concat(concat(card1, card2), cards)
            .collect(Collectors.toList());
    }

    private HandCards(List<Card> cards) {
        this.cards = cards;
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
    public Cards openedCards(int count) {
        return Cards.toUnmodifiable(new HandCards(cards.subList(0, count)));
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
