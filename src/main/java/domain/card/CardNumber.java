package domain.card;

import java.util.Arrays;

public enum CardNumber {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private int cardNumber;

    CardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public static CardNumber findCardNumber(int cardNumber) {
        return Arrays.stream(values())
                .filter(card -> card.cardNumber == cardNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getCardNumber() {
        return this.cardNumber;
    }
}
