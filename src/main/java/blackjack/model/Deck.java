package blackjack.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final Deque<Card> deck;

    public Deck() {
        this.deck = makeDeck();
    }

    private static Deque<Card> makeDeck() {
        List<Card> originDeck = Arrays.stream(Shape.values())
                .flatMap(Deck::matchScore)
                .collect(Collectors.toList());
        return new ArrayDeque<>(shuffleDeck(originDeck));
    }

    private static Deque<Card> shuffleDeck(final List<Card> originDeck) {
        Collections.shuffle(originDeck);
        return new ArrayDeque<>(originDeck);
    }

    private static Stream<Card> matchScore(Shape shape) {
        return Arrays.stream(Score.values())
                .map(score -> new Card(shape, score));
    }

    public List<Cards> distributeInitialCard(final int playerCount) {
        final List<Cards> cards = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            List<Card> twoCard = List.of(distribute(), distribute());
            cards.add(new Cards(twoCard));
        }

        return cards;
    }

    public Card distribute() {
        try {
            return deck.removeFirst();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("카드가 부족합니다.");
        }
    }

    public Deque<Card> getDeck() {
        return deck;
    }
}
