package blackjack.model;

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
    JACK(11),
    QUEEN(12),
    KING(13);

    private final int order;

    CardNumber(int order) {
        this.order = order;
    }

    public static CardNumber generate(int number) {
        return Arrays.stream(values())
                .filter(cardNumber -> cardNumber.order == number)
                .findFirst()
                .orElseThrow();
    }
}
