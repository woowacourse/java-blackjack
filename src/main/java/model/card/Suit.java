package model.card;

import java.util.Arrays;

public enum Suit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static Suit findByName(String name) {
        return Arrays.stream(Suit.values())
                .filter(suit -> suit.name.equals(name))
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}
