package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {
    private static final Map<Integer, Card> CACHE = new HashMap<>();

    private final Suit suit;
    private final Number number;

    private Card(Suit suit, Number number) {
        this.suit = suit;
        this.number = number;
    }

    public static Card of(Suit suit, Number number) {
        return CACHE.computeIfAbsent(toKey(suit, number), ignored -> new Card(suit, number));
    }

    private static Integer toKey(Suit suit, Number number) {
        return Objects.hash(suit, number);
    }

    public boolean isAce() {
        return number == Number.ACE;
    }

    public int score() {
        return number.score();
    }

    public String suit() {
        return suit.value();
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
        return suit == card.suit && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, number);
    }

}
