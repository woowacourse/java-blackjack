package view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Denomination {
    ACE("A"),
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

    private final String symbol;

    Denomination(final String symbol) {
        this.symbol = symbol;
    }

    public static String messageOf(final String value) {
        return Arrays.stream(values())
                .filter(d -> d.name().equalsIgnoreCase(value))
                .findFirst()
                .map(d -> d.symbol)
                .orElseThrow(() -> new NoSuchElementException(value + " 에 해당하는 심볼이 없습니다."));
    }
}
