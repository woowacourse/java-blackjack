package blackjack.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {
    private static final Map<Integer, Card> cache = new HashMap<>();
    private final CardSuit suit;
    private final CardNumber number;

    private Card(CardSuit suit, CardNumber number) {
        this.suit = suit;
        this.number = number;
    }

    public static Card of(CardSuit suit, CardNumber number) {
        int cardHash = Objects.hash(suit, number);
        if (cache.containsKey(cardHash)) {
            return cache.get(cardHash);
        }
        Card card = new Card(suit, number);
        cache.put(card.hashCode(), card);
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, number);
    }
}
