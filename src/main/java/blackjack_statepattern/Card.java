package blackjack_statepattern;

import java.util.HashMap;
import java.util.Map;

public final class Card {

    private static final Map<String, Card> cache = new HashMap<>(52);

    private final Suit suit;
    private final Denomination denomination;

    private Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(final Suit suit, final Denomination denomination) {
        return cache.computeIfAbsent(toKey(suit, denomination), ignored -> new Card(suit, denomination));
    }

    private static String toKey(Suit suit, Denomination denomination) {
        return suit.name() + denomination.name();
    }
}
