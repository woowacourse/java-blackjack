package model;

import java.util.Arrays;

public enum Suits {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String name;

    Suits(String name) {
        this.name = name;
    }

    public static Suits findByName(String name) {
        return Arrays.stream(Suits.values())
                .filter(suits -> suits.name.equals(name))
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}
