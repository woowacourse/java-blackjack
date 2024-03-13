package domain;

import java.util.Arrays;
import java.util.List;

public enum Suit {
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    SPADES("스페이드"),
    CLUBS("클로버");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static List<Suit> getValues() {
        return Arrays.stream(Suit.values())
                .toList();
    }

    public String getName() {
        return this.name;
    }
}
