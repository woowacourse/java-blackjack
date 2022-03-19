package blackjack_statepattern.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    private static String toKey(final Suit suit, final Denomination denomination) {
        return suit.name() + denomination.name();
    }

    public int score() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
