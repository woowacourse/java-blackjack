package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {

    private static final List<Card> CACHE = new ArrayList<>();
    private static final String NOT_FOUND_ERROR = "조건에 맞는 카드가 존재하지 않습니다.";

    static {
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                CACHE.add(new Card(denomination, suit));
            }
        }
    }

    private final Denomination denomination;
    private final Suit suit;

    private Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card valueOf(Denomination denomination, Suit suit) {
        return CACHE.stream()
                .filter(card -> card.denomination == denomination)
                .filter(card -> card.suit == suit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_ERROR));
    }

    public static List<Card> cache() {
        return new ArrayList<>(CACHE);
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public String getDenominationName() {
        return denomination.getName();
    }

    public int getDenominationValue() {
        return this.denomination.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "denomination=" + denomination +
                ", suit=" + suit +
                '}';
    }
}
