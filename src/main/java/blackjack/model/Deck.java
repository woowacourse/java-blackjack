package blackjack.model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final Deque<Card> deck = new ArrayDeque<>();

    static {
        makeDeck();
    }

    private static void makeDeck() {
        deck.addAll(Arrays.stream(Shape.values())
                .flatMap(Deck::matchScore)
                .collect(Collectors.toCollection(ArrayDeque::new)));
    }

    private static Stream<Card> matchScore(Shape shape) {
        return Arrays.stream(Score.values())
                .map(score -> new Card(shape, score));
    }

    public static Deque<Card> getDeck() {
        return deck;
    }
}
