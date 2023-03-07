package model.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Deck {

    private final Deque<Card> cards;

    private Deck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public static Deck create() {
        final List<Card> cards = createCards();

        Collections.shuffle(cards);

        return new Deck(cards);
    }

    private static List<Card> createCards() {
        return stream(Shape.values())
                .flatMap(Deck::createCard)
                .collect(toList());
    }

    private static Stream<Card> createCard(final Shape shape) {
        return stream(Value.values())
                .map(value -> new Card(shape, value));
    }

    public Card pick() {
        return cards.pop();
    }
}
