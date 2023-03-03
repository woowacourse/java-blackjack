package blackjack.view;

import blackjack.domain.CardNumber;
import java.util.stream.Stream;

public enum ViewCardNumber {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ;

    final String name;

    ViewCardNumber(String name) {
        this.name = name;
    }

    public static ViewCardNumber getCardNumber(CardNumber cardNumber) {
        return Stream.of(ViewCardNumber.values())
            .filter(number -> number.equals(cardNumber))
            .findFirst()
            .get();
    }

    public String getName() {
        return name;
    }
}
