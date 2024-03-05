package domain;

import java.util.Arrays;

enum CardNumber {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    J(10),
    Q(10),
    K(10);

    private final int number;

    CardNumber(int number) {
        this.number = number;
    }

    public static CardNumber find(int rawNumber) {
        return Arrays.stream(values())
                .filter(cardNumber -> cardNumber.number == rawNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
