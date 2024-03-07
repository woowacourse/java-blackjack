package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum Denomination {
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


    private final int value;

    Denomination(final int value) {
        this.value = value;
    }

    public int getValue(int score) {
        if (this == ACE && score + 11 <= 21) {
            return 11;

        }
        return this.value;
    }

    public static Denomination getDenomination(int number) {
        return Arrays.stream(Denomination.values())
                .filter(denomination -> denomination.ordinal() == number)
                .findAny()
                .orElseThrow();
    }
}
