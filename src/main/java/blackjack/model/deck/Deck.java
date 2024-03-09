package blackjack.model.deck;

import blackjack.model.participant.Hand;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Deck {
    private final Deque<Card> deck;

    public Deck() {
        this.deck = makeDeck();
    }

    private Deque<Card> makeDeck() {
        List<Card> originDeck = Arrays.stream(Shape.values())
                .flatMap(Deck::matchScore)
                .collect(Collectors.toList());
        return new ArrayDeque<>(shuffleDeck(originDeck));
    }

    private static Stream<Card> matchScore(Shape shape) {
        return Arrays.stream(Score.values())
                .map(score -> new Card(shape, score));
    }

    private Deque<Card> shuffleDeck(final List<Card> originDeck) {
        Collections.shuffle(originDeck);
        return new ArrayDeque<>(originDeck);
    }

    public Hand distributeInitialCard() {
        return new Hand(List.of(distribute(), distribute()));
    }

    public List<Hand> distributeInitialCard(final int playerCount) {
        return IntStream.range(0, playerCount)
                .mapToObj(i -> distributeInitialCard())
                .toList();
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
