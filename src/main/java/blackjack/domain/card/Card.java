package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {

    private static final List<Card> CACHE;

    private final Number number;
    private final Suit suit;

    private Card(final Number number, final Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    static {
        final List<Card> cache = new ArrayList<>();
        for (final Number number : Number.values()) {
            cache.addAll(createSuits(number));
        }
        CACHE = Collections.unmodifiableList(cache);
    }

    private static List<Card> createSuits(final Number number) {
        final List<Card> suits = new ArrayList<>();
        for (final Suit suit : Suit.values()) {
            suits.add(new Card(number, suit));
        }
        return suits;
    }

    public static Card of(final Number number, final Suit suit) {
        return CACHE.get((number.ordinal() * Suit.values().length) + suit.ordinal());
    }

    public Number getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }
}
