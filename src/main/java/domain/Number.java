package domain;

import java.util.Arrays;

public enum Number {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    JACK,
    QUEEN,
    KING;


    public static Number findBy(final int index) {
        return Arrays.stream(Number.values())
                .filter(number -> number.ordinal() == index)
                .findFirst()
                .orElseThrow();
    }
}
