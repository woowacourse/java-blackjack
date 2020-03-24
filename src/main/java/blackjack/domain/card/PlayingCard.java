package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public class PlayingCard {
    private final Suit suit;
    private final Denomination denomination;

    private static final Map<String, PlayingCard> CARDS = new HashMap<>();

    static {
        for (final Suit suit : Suit.values()) {
            for (final Denomination denomination : Denomination.values()) {
                CARDS.put(toKey(suit, denomination), new PlayingCard(suit, denomination));
            }
        }
    }

    private PlayingCard(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static PlayingCard of(final Suit suit, final Denomination denomination) {
        return CARDS.get(toKey(suit, denomination));
    }

    private static String toKey(final Suit suit, final Denomination denomination) {
        return suit.name() + denomination.name();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public int getScore() {
        return denomination.getScore();
    }
}
