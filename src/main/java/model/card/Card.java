package model.card;

import java.util.*;
import java.util.stream.Collectors;

public class Card {
    private static final Set<Card> cardDeck;

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    static {
        cardDeck = Arrays.stream(CardShape.values())
                .flatMap(newSuit -> Arrays.stream(CardNumber.values())
                        .map(newDenomination -> new Card(newSuit, newDenomination)))
                .collect(Collectors.toUnmodifiableSet());
    }

    private Card(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public static Card of(CardShape cardShape, CardNumber cardNumber) {
        return cardDeck.stream()
                .filter(card -> card.cardShape == cardShape && card.cardNumber == cardNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(cardShape.name() + cardNumber.name() + " 카드는 존재하지 않습니다."));
    }

    public static List<Card> createCardDeck() {
        return new ArrayList<>(cardDeck);
    }

    public int minimumNumber() {
        return cardNumber.minimumNumber();
    }

    public int subtractMaxMinNumber() {
        return cardNumber.maximumNumber() - cardNumber.minimumNumber();
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
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
        return cardShape == card.cardShape && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardNumber);
    }
}
