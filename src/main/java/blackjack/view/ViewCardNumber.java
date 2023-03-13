package blackjack.view;

import java.util.Arrays;

public enum ViewCardNumber {
    ACE("ACE", "A"),
    TWO("TWO", "2"),
    THREE("THREE", "3"),
    FOUR("FOUR", "4"),
    FIVE("FIVE", "5"),
    SIX("SIX", "6"),
    SEVEN("SEVEN", "7"),
    EIGHT("EIGHT", "8"),
    NINE("NINE", "9"),
    TEN("TEN", "10"),
    JACK("JACK", "J"),
    QUEEN("QUEEN", "Q"),
    KING("KING", "K"),
    ;

    final String cardNumberName;
    final String name;

    ViewCardNumber(final String cardNumberName, final String name) {
        this.name = name;
        this.cardNumberName = cardNumberName;
    }

    public static String getCardNumber(final String cardNumber) {
        return Arrays.stream(ViewCardNumber.values())
                .filter(it -> it.cardNumberName.equals(cardNumber))
                .findAny()
                .get()
                .name;
    }
}
