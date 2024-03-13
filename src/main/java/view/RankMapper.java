package view;

import java.util.Arrays;

public enum RankMapper {
    ACE("A"),

    ONE("1"),

    TWO("2"),

    THREE("3"),

    FOUR("4"),

    FIVE("5"),

    SIX("6"),

    SEVEN("7"),

    EIGHT("8"),

    NINE("9"),

    TEN("10"),

    JACK("J"),

    QUEEN("Q"),

    KING("K");

    private final String value;

    RankMapper(final String value) {
        this.value = value;
    }

    public static String map(final String toFind) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(toFind))
                .map(value -> value.value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("찾으려는 값, %s가 존재하지 않습니다", toFind)));
    }
}
