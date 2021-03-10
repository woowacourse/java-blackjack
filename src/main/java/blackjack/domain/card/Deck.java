package blackjack.domain.card;

import blackjack.domain.user.Cards;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final int START_COUNT = 0;
    private final Deque<Card> deck;

    public Deck() {
        this.deck = createDeck();
    }

    private Deque<Card> createDeck() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Shape.values())
                .forEach(shape -> cards.addAll(createByShape(shape)));
        shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private List<Card> createByShape(Shape shape) {
        return Arrays.stream(Value.values())
                .map(value -> new Card(shape, value))
                .collect(Collectors.toList());
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public Cards popTwo() {
        return new Cards(IntStream.range(START_COUNT, 2)
                .mapToObj(c -> deck.pop())
                .collect(Collectors.toList()));
    }

    public Cards popOne() {
        return new Cards(IntStream.range(START_COUNT, 1)
                .mapToObj(c -> deck.pop())
                .collect(Collectors.toList()));
    }
}
