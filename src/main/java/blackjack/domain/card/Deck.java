package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private static final String NO_MORE_CARD_ERROR_MSG = "덱에 더 이상 남은 카드가 없습니다.";

    private final LinkedList<Card> deck;

    private Deck(List<Card> deck) {
        this.deck = new LinkedList<>(deck);
    }

    public static Deck createDeck() {
        List<Card> deck = Arrays.stream(Suit.values())
                .flatMap(suit -> iterateValues(suit).stream())
                .collect(Collectors.toList());

        shuffleDeck(deck);

        return new Deck(deck);
    }

    private static List<Card> iterateValues(Suit suit) {
        return Value.of().stream()
                .map(value -> new Card(suit, value))
                .collect(Collectors.toList());
    }

    private static void shuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);
    }

    public Card draw() {
        try {
            return deck.pop();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(NO_MORE_CARD_ERROR_MSG);
        }
    }

    public int size() {
        return deck.size();
    }
}