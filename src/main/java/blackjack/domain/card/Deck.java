package blackjack.domain.card;

import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Value;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private final LinkedList<Card> deck;
    private static final String NO_MORE_CARD_ERROR_MSG = "덱에 더 이상 남은 카드가 없습니다.";

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
        return Arrays.stream(Value.values())
                .filter(value -> value != Value.ACE_OF_ONE)
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