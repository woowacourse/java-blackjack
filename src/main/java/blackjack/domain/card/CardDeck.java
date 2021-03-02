package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {
    private final Deque<Card> deck;

    private CardDeck(Deque<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck createDeck() {
        Deque<Card> deck = new ArrayDeque<>(Arrays.stream(CardNumber.values())
                .flatMap(CardDeck::mapToSuit)
                .collect(Collectors.toList()));
        return new CardDeck(deck);
    }

    private static Stream<Card> mapToSuit(CardNumber cardNumber) {
        return Arrays.stream(Suit.values())
                .map(suit -> new Card(suit, cardNumber));
    }

    public Deque<Card> getDeck() {
        return deck;
    }
}
