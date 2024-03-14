package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Card {

    private static final Map<String, Card> CARD_CACHE = new LinkedHashMap<>();
    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    static {
        List<Suit> suits = Suit.getValues();
        for (Suit suit : suits) {
            cacheCardsForSuit(suit);
        }
    }

    private static void cacheCardsForSuit(Suit suit) {
        List<Rank> ranks = Rank.getValues();
        for (Rank rank : ranks) {
            CARD_CACHE.put(toKey(rank, suit), new Card(rank, suit));
        }
    }

    private static String toKey(Rank rank, Suit suit) {
        return rank.getText() + suit.getName();
    }

    public static Card of(Rank rank, Suit suit) {
        return CARD_CACHE.get(toKey(rank, suit));
    }

    public static List<Card> getAllCards() {
        return new ArrayList<>(CARD_CACHE.values());
    }

    public boolean isAceCard() {
        return this.rank.isAce();
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public String getRankText() {
        return rank.getText();
    }

    public String getSuit() {
        return suit.getName();
    }
}
