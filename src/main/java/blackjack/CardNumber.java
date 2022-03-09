package blackjack;

import rentcompany.Car;

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

    public static final String ERROR_INVALID_CARD_NUMBER = "[ERROR] 올바른 카드 번호가 아닙니다.";

    private final int number;

    CardNumber(int number) {
        this.number = number;
    }

    public static CardNumber of(int number) {
        return Arrays.stream(values())
                .filter(cardNumber -> cardNumber.number == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_INVALID_CARD_NUMBER));
    }

    public int getNumber() {
        return number;
    }
}
