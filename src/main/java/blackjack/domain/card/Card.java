package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    private static final Map<String, Card> CARDS = new HashMap<>();

    static {
        for (final Suit suit : Suit.values()) {
            putDenomination(suit);
        }
    }

    private static void putDenomination(Suit suit) {
        for (final Denomination denomination : Denomination.values()) {
            CARDS.put(toKey(suit, denomination), new Card(suit, denomination));
        }
    }

    private Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(final Suit suit, final Denomination denomination) {
        return CARDS.get(toKey(suit, denomination));
    }

    private static String toKey(final Suit suit, final Denomination denomination) {
        return denomination.name() + suit.name();
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

    public static List<Card> makeAllCards() {
        return new ArrayList<>(CARDS.values());
    }
}