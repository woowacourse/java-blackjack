package domain;

import java.util.Arrays;
import java.util.List;

public enum Letter {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10),
    A(11);

    private final int value;

    Letter(int value) {
        this.value = value;
    }

    public static List<Letter> getValues() {
        return Arrays.stream(Letter.values())
                .toList();
    }

    public boolean isAce() {
        return this == A;
    }

    public int getValue() {
        return value;
    }
}
