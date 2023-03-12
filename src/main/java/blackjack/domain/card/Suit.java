package blackjack.domain.card;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Suit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static Stream<Suit> stream() {
        return Arrays.stream(values());
    }

    public String getName() {
        return name;
    }
}
