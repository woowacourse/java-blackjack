package blackjack.domain.card;

import java.util.*;

public class Card {

    private static final List<Card> ORIGINAL_CARDS = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            Arrays.stream(Denomination.values())
                    .forEach(denomination -> ORIGINAL_CARDS.add(new Card(suit, denomination)));
        }
    }

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static LinkedList<Card> getCachingCards() {
        return new LinkedList<>(ORIGINAL_CARDS);
    }

    public Score getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public String getDenominationValue() {
        return denomination.denomination();
    }

    public String getSuitValue() {
        return suit.suit();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return denomination == card.denomination && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
