package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    public static final int TOTAL_CARD_COUNT = 52;

    private static List<Card> deck;
    private int cardUsed = 0;

    public Deck(List<Card> deck) {
        Deck.deck = deck;
    }

    public static Deck of() {
        List<Card> deck = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Face.values()).map(face -> new Card(suit, face)))
                .collect(Collectors.toList());
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    public Card draw() {
        if (cardUsed == TOTAL_CARD_COUNT) {
            shuffle();
            cardUsed = 0;
        }
        return deck.get(cardUsed++);
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }
}