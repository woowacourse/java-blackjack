package domain;

import java.util.Collections;
import java.util.List;

public enum TrumpNumber {

    TWO(List.of(2), "2"),
    THREE(List.of(3), "3"),
    FOUR(List.of(4), "4"),
    FIVE(List.of(5), "5"),
    SIX(List.of(6), "6"),
    SEVEN(List.of(7), "7"),
    EIGHT(List.of(8), "8"),
    NINE(List.of(9), "9"),
    TEN(List.of(10), "10"),
    ACE(List.of(1, 11), "A"),
    JACK(List.of(10), "J"),
    KING(List.of(10), "K"),
    QUEEN(List.of(10), "Q");

    private final List<Integer> score;
    private final String value;

    TrumpNumber(List<Integer> score, String value) {
        this.score = score;
        this.value = value;
    }

    public List<Integer> getScore() {
        return Collections.unmodifiableList(score);
    }

    public String getValue() {
        return value;
    }
}
