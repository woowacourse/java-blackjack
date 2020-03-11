package domain;

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
    JAY(11),
    QUEEN(12),
    KING(13);

    private int cardNumber;

    private CardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public static CardNumber getCardNumber(int cardNumber) {
        return Arrays.stream(values())
                .filter(card -> card.cardNumber == cardNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
