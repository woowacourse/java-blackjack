package blackjack.domain;

import java.util.Arrays;

public enum Number {

    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J");

    private final int value;
    private final String word;

    Number(int value, String word) {
        this.value = value;
        this.word = word;
    }

    public static String toWord(Number findNumber) {
        return Arrays.stream(values())
                .filter(number -> number == findNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 숫자가 존재하지 않습니다."))
                .getWord();
    }

    public int getValue() {
        return value;
    }

    public String getWord() {
        return word;
    }
}
