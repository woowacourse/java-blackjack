package blackjack.domain;

import java.util.Arrays;
import java.util.List;

public enum Deck {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;
    private final List<Number> numbers;

    Deck(String name) {
        this.name = name;
        numbers = Arrays.asList(Number.values());
    }
}
