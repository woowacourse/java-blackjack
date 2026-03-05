package domain;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum Number {
    ACE(11),
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

    private Number(int value) {
        this.value = value;
    }

    public static Number from(int value){
        return Arrays.stream(values())
                .filter(number -> number.value == value)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("ERROR"));
    }

    public int getValue() {
        return value;
    }
}
