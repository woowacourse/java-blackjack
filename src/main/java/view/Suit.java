package view;

import java.util.Arrays;

public enum Suit {
    HEART("하트"),
    CLUBS("클로버"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public static String messageOf(final String value) {
        return Arrays.stream(values())
                .filter(suit -> suit.name().equalsIgnoreCase(value))
                .findFirst()
                .map(suit -> suit.name)
                .orElse(null);
    }
}
