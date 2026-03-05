package blackjack.domain;

import java.util.List;

public enum Suit {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static List<Suit> all() {
        return List.of(values());
    }
}
