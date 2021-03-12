package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card {

    private static final Map<String, Card> CARDS = new HashMap<>();

    static {
        for (final Suit suit : Suit.values()) {
            putDenomination(suit);
        }
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    private static void putDenomination(final Suit suit) {
        for (final Denomination denomination : Denomination.values()) {
            CARDS.put(toKey(suit, denomination), new Card(suit, denomination));
        }
    }

    public static Card of(final Suit suit, final Denomination denomination) {
        return CARDS.get(toKey(suit, denomination));
    }

    private static String toKey(final Suit suit, final Denomination denomination) {
        return denomination.name() + suit.name();
    }

    public static List<Card> makeAllCards() {
        return new ArrayList<>(CARDS.values());
    }

    public String getName() {
        return denomination.getDenomination() + suit.getSuit();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public int getScore() {
        return denomination.getScore();
    }
}