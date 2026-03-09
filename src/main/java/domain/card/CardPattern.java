package domain.card;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum CardPattern {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String name;

    CardPattern(String name) {
        this.name = name;
    }

    public static CardPattern matchCardPattern(String value) {
        return Arrays.stream(CardPattern.values()).filter(pattern ->  pattern.name.equals(value))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public String getName() {
        return name;
    }
}
