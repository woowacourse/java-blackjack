package domain;

import java.util.Arrays;
import java.util.List;

public enum Letter {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    J("J", 10),
    Q("Q", 10),
    K("K", 10),
    A("A", 11);

    private final String text;
    private final int value;

    Letter(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static List<Letter> getValues() {
        return Arrays.stream(Letter.values())
                .toList();
    }

    public boolean isAce() {
        return this == A;
    }

    public String getText() {
        return this.text;
    }

    public int getValue() {
        return this.value;
    }
}
