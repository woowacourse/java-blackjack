package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static Map<String, Card> cache = new HashMap<>(52);

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
        return suit.getValue() + denomination.getValue();
    }


    public boolean isAceCard() {
        return denomination == Denomination.ACE;
    }

    public String getSuit() {
        return suit.getValue();
    }

    public String getDenominationType() {
        return denomination.getType();
    }

    public int getDenomination() {
        return denomination.getValue();
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
