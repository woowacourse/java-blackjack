package domain;

import java.util.Arrays;

public enum Rank {
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


    public static Rank findBy(final int index) {
        return Arrays.stream(Rank.values())
                .filter(number -> number.ordinal() == index)
                .findFirst()
                .orElseThrow();
    }
}
