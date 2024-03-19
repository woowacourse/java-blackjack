package domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private final Deque<Card> deck;

    public Deck() {
        deck = createShuffledDeck();
    }

    private Deque<Card> createShuffledDeck() {
        final List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private List<Card> createCards() {
        return Arrays.stream(Denomination.values())
                .flatMap(denomination ->
                        Arrays.stream(Suit.values())
                                .map(symbol -> new Card(denomination, symbol)))
                .collect(Collectors.toList());
    }
    
    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("덱이 비어있습니다");
        }
        return deck.removeLast();
    }
}

