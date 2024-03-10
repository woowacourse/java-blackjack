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
    private static final int BLACKJACK_CARD_COUNT = 52;
    
    private final Deque<Card> deck;

    public Deck() {
        final Deque<Card> newDeck = makeDeck();
        validateSize(newDeck);
        this.deck = makeDeck();
    }

    private void validateSize(final Deque<Card> deck) {
        if (deck.size() != BLACKJACK_CARD_COUNT) {
            throw new IllegalStateException("카드의 수가 52개가 아닙니다.");
        }
    }

    private Deque<Card> makeDeck() {
        List<Card> originDeck = Arrays.stream(Shape.values())
                .flatMap(this::matchScore)
                .collect(Collectors.toList());
        return shuffleDeck(originDeck);
    }

    private Stream<Card> matchScore(Shape shape) {
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
