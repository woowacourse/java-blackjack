package blackjack.domain;

import java.util.Arrays;
import java.util.List;

public enum CardPattern {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;

    private final String name;

    CardPattern(final String name) {
        this.name = name;
    }

    public static List<CardPattern> cardPatterns() {
        return Arrays.asList(values());
    }
}
