package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {

    private static final List<Card> CACHE;

    private final Rank rank;
    private final Suit suit;

    private Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    static {
        final List<Card> cache = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            cache.addAll(createSuits(rank));
        }
        CACHE = Collections.unmodifiableList(cache);
    }

    private static List<Card> createSuits(final Rank rank) {
        final List<Card> suits = new ArrayList<>();
        for (final Suit suit : Suit.values()) {
            suits.add(new Card(rank, suit));
        }
        return suits;
    }

    public static Card of(final Rank rank, final Suit suit) {
        return CACHE.get((rank.ordinal() * Suit.values().length) + suit.ordinal());
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}
