package blackjack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {
    private static final Map<Suit, Map<Denomination, Card>> CACHE = new HashMap<>();

    static {
        for (Suit suit : Suit.values()) {
            CACHE.put(suit, new HashMap<>());
            for (Denomination denomination : Denomination.values()) {
                CACHE.get(suit).put(denomination, new Card(suit, denomination));
            }
        }
    }

    private final Denomination denomination;
    private final Suit suit;

    private Card(Suit suit, Denomination denomination) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card generate(Suit suit, Denomination denomination) {
        return CACHE.get(suit).get(denomination);
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
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
