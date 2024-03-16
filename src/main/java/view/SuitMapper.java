package view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum SuitMapper {
    HEARTS("하트"),
    CLUBS("클로버"),
    SPADES("스페이드"),
    DIAMONDS("다이아몬드");

    private final String value;

    SuitMapper(final String value) {
        this.value = value;
    }

    public static String map(final String toFind) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(toFind))
                .map(value -> value.value)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("찾으려는 값, %s이 존재하지 않습니다.",toFind)));
    }
}
