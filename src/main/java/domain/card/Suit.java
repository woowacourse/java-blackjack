package domain.card;

import java.util.Arrays;
import java.util.List;

public enum Suit {

    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    ;

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static List<Suit> getAllSuits() {
        return Arrays.stream(values())
                .toList();
    }

    public String getName() {
        return name;
    }
}
