package domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Value {
    ACE("A", 11), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5),
    SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10),
    JACK("J", 10), QUEEN("Q", 10), KING("K", 10);

    private final String value;
    private final int score;

    Value(final String value, final int score) {
        this.value = value;
        this.score = score;
    }

    public static Value of(final String value) {
        return Arrays.stream(Value.values())
                .filter(element -> element.value.equals(value))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public String getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }
}
