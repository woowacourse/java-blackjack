package model.card;

import java.util.*;
import java.util.stream.Collectors;

public class Card {
    private static final Set<Card> cardDeck;

    private final Suit suit;
    private final Denomination denomination;

    static {
        cardDeck = Arrays.stream(Suit.values())
                .flatMap(newSuit -> Arrays.stream(Denomination.values())
                        .map(newDenomination -> new Card(newSuit, newDenomination)))
                .collect(Collectors.toUnmodifiableSet());
    }

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static List<Card> createCardDeck() {
        return new ArrayList<>(cardDeck);
    }

    public static Card of(Suit suit, Denomination denomination) {
        return cardDeck.stream()
                .filter(card -> card.suit == suit && card.denomination == denomination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(suit.name() + denomination.name() + " 카드는 존재하지 않습니다."));
    }

    public int minimumNumber() {
        return denomination.minimumNumber();
    }

    public int subtractMaxMinNumber() {
        return denomination.maximumNumber() - denomination.minimumNumber();
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
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
