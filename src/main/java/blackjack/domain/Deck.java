package blackjack.domain;

import java.util.*;

public class Deck {

    private static final Map<Integer, Card> deck = new HashMap<>();

    static {
        makeDeck();
    }

    private Deck() {
    }

    private static void makeDeck() {
        int count = 0;
        for (Rank rank : Rank.values()) {
            count = addCardsToDeck(rank, count);
        }
    }

    private static int addCardsToDeck(final Rank rank, int count) {
        for (Suit suit : Suit.values()) {
            deck.put(count, new Card(rank, suit));
            count++;
        }
        return count;
    }

    public static Card from(final int index) {
        return deck.get(index);
    }

    public static List<Integer> getKeys() {
        return new ArrayList<>(deck.keySet());
    }
}
