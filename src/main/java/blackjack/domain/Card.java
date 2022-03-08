package blackjack.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Card {

    public static final Set<Card> VALUES;

    static {
        Set<Card> cards = new HashSet<>();
        for (Denomination denomination : Denomination.values()) {
            createCard(cards, denomination);
        }

        VALUES = Collections.unmodifiableSet(cards);
    }

    private final Denomination denomination;
    private final Suit suit;

    private Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    private static void createCard(Set<Card> cards, Denomination denomination) {
        for (Suit suit : Suit.values()) {
            Card card = new Card(denomination, suit);
            cards.add(card);
        }
    }

    public static Card of(Denomination denomination, Suit suit) {
        Card targetCard = new Card(denomination, suit);

        return VALUES.stream()
                .filter(card -> card.equals(targetCard))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    public int getScore() {
        return denomination.getScore();
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
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
