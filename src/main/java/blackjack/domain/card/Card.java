package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Card {

    private static final List<Card> TOTAL_CARD_CACHE = new ArrayList<>();

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    static {
        for (CardNumber cardNumber : CardNumber.values()) {
            insertCardCache(cardNumber);
        }
    }

    private static void insertCardCache(final CardNumber cardNumber) {
        for (CardShape cardShape : CardShape.values()) {
            TOTAL_CARD_CACHE.add(new Card(cardNumber, cardShape));
        }
    }

    private Card(final CardNumber cardNumber, final CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public static Card of(final CardNumber cardNumber, final CardShape cardShape) {
        Card newCard = new Card(cardNumber, cardShape);
        return TOTAL_CARD_CACHE.stream()
                .filter(card -> card.equals(newCard))
                .findAny()
                .orElse(newCard);
    }

    public static List<Card> getTotalCard() {
        return Collections.unmodifiableList(TOTAL_CARD_CACHE);
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public String getName() {
        return cardNumber.getName() + cardShape.getShape();
    }

    @Override
    public boolean equals(final Object otherCard) {
        if (this == otherCard) {
            return true;
        }
        if (!(otherCard instanceof Card)) {
            return false;
        }
        Card card = (Card) otherCard;
        return cardNumber == card.cardNumber && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardShape);
    }
}
