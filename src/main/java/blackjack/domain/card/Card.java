package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> CACHE_DECK = new HashMap<>();

    private final Number number;
    private final Suit suit;

    static {
        for (Suit suit : Suit.values()) {
            for (Number number : Number.values()) {
                CACHE_DECK.put(createKey(number, suit), new Card(number, suit));
            }
        }
    }

    private Card(final Number number, final Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public static Card of(final Number number, final Suit suit) {
        return CACHE_DECK.get(createKey(number, suit));
    }

    private static String createKey(Number number, Suit suit) {
        return number.getName() + suit.getName();
    }

    public static List<Card> getDeck() {
        return new ArrayList<>(CACHE_DECK.values());
    }

    public String getDenomination() {
        return number.getName();
    }

    public String getSuit() {
        return suit.getName();
    }

    public int toInt() {
        return this.number.getValue();
    }

    public boolean isAce() {
        return number.isAce();
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
        return number == card.number && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "denomination=" + number +
                ", suit=" + suit +
                '}';
    }
}
