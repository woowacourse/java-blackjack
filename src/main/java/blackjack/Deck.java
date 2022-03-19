package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private static final String EMPTY_DECK_EXCEPTION = "[ERROR] 덱이 비었습니다.";
    private final Queue<Card> deck;

    private Deck(List<Card> cards) {
        deck = new LinkedList<>(cards);
    }

    private static List<Card> makeSpecificSuitCards(Suit suit) {
        List<Card> specificSuitCards = new ArrayList<>();
        for (Denomination denomination : Denomination.values()) {
            specificSuitCards.add(Card.generate(suit, denomination));
        }
        return specificSuitCards;
    }

    public static Deck makeRandomShuffledDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            cards.addAll(makeSpecificSuitCards(suit));
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public static Deck makeIntendedShuffledDeck(List<Card> cards) {
        return new Deck(cards);
    }

    public Card pickTopCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_EXCEPTION);
        }
        return deck.poll();
    }
}
