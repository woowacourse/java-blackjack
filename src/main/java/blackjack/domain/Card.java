package blackjack.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Card {
    private static final Map<String, Card> cache = new ConcurrentHashMap<>(52);
    private final Suit suit;
    private final Letter letter;

    private Card(Suit suit, Letter letter) {
        this.suit = suit;
        this.letter = letter;
    }

    public static Card of(final Suit suit, final Letter letter) {
        return cache.computeIfAbsent(toKey(suit, letter), ignored -> new Card(suit, letter));
    }

    public static String toKey(final Suit suit, final Letter letter) {
        return suit.name() + letter.name();
    }

    public boolean isAce() {
        return letter.isAce();
    }

    public Suit getCardSuit() {
        return suit;
    }

    public Letter getCardLetter() {
        return letter;
    }

    public Score getScore() {
        return new Score(letter.getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && letter == card.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, letter);
    }
}
