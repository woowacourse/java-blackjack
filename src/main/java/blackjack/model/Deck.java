package blackjack.model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final Deque<Card> deck = new ArrayDeque<>();

    static {
        makeDeck();
    }

    private static void makeDeck() {
        List<Card> originDeck = Arrays.stream(Shape.values())
                .flatMap(Deck::matchScore)
                .collect(Collectors.toList());
        deck.addAll(shuffleDeck(originDeck));
    }

    private static Deque<Card> shuffleDeck(final List<Card> originDeck) {
        Collections.shuffle(originDeck);
        return new ArrayDeque<>(originDeck);
    }

    private static Stream<Card> matchScore(Shape shape) {
        return Arrays.stream(Score.values())
                .map(score -> new Card(shape, score));
    }

    public static Deque<Card> getDeck() {
        return deck;
    }
}
