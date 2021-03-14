package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final Deque<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = new ArrayDeque<>(deck);
    }

    public Card draw() {
        return deck.pop();
    }

    public static Deck createShuffledDeck() {
        List<Card> deck = createDeck();
        Collections.shuffle(deck);

        return new Deck(deck);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());
    }
}
