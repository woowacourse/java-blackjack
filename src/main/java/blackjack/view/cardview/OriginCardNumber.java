package blackjack.view.cardview;

import blackjack.domain.card.CardNumber;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum OriginCardNumber {

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
    KING(CardNumber.KING, "K");

    private final CardNumber cardNumber;
    private final String name;

    OriginCardNumber(CardNumber cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    public static String getOriginalName(CardNumber cardNumber) {
        return Arrays.stream(values())
                .filter(c -> c.getCardNumber() == cardNumber)
                .map(OriginCardNumber::getname)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    private CardNumber getCardNumber() {
        return cardNumber;
    }

    private String getname() {
        return name;
    }
}
