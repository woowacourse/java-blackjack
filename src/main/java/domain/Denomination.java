package domain;

import java.util.Arrays;

public enum Denomination {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    public static Denomination getDenomination(int number) {
        return Arrays.stream(Denomination.values())
                .filter(denomination -> denomination.ordinal() == number)
                .findAny()
                .orElseThrow();
    }
}
