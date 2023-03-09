package blackjack.view;

import blackjack.domain.card.CardNumber;

import java.util.Arrays;

public enum ViewCardNumber {
    ACE(CardNumber.ACE, "A"),
    TWO(CardNumber.TWO, "2"),
    THREE(CardNumber.THREE, "3"),
    FOUR(CardNumber.FOUR, "4"),
    FIVE(CardNumber.FIVE, "5"),
    SIX(CardNumber.SIX, "6"),
    SEVEN(CardNumber.SEVEN, "7"),
    EIGHT(CardNumber.EIGHT, "8"),
    NINE(CardNumber.NINE, "9"),
    TEN(CardNumber.TEN, "10"),
    JACK(CardNumber.JACK, "J"),
    QUEEN(CardNumber.QUEEN, "Q"),
    KING(CardNumber.KING, "K"),
    ;

    final CardNumber cardNumber;
    final String name;

    ViewCardNumber(CardNumber cardNumber, String name) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    public static String getCardNumber(final CardNumber cardNumber) {
        return Arrays.stream(ViewCardNumber.values())
                .filter(it -> it.cardNumber == cardNumber)
                .findAny()
                .get()
                .name;
    }
}
