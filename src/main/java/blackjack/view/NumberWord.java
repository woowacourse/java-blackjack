package blackjack.view;

import blackjack.domain.Number;

import java.util.Arrays;

public enum NumberWord {

    ACE(Number.ACE, "A"),
    TWO(Number.TWO, "2"),
    THREE(Number.THREE, "3"),
    FOUR(Number.FOUR, "4"),
    FIVE(Number.FIVE, "5"),
    SIX(Number.SIX, "6"),
    SEVEN(Number.SEVEN, "7"),
    EIGHT(Number.EIGHT, "8"),
    NINE(Number.NINE, "9"),
    TEN(Number.TEN, "10"),
    KING(Number.KING, "K"),
    QUEEN(Number.QUEEN, "Q"),
    JACK(Number.JACK, "J");

    private final Number number;
    private final String word;

    NumberWord(Number number, String word) {
        this.number = number;
        this.word = word;
    }

    public static String toWord(Number findNumber) {
        NumberWord numberWord = Arrays.stream(values())
                .filter(number -> number.number == findNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 숫자가 존재하지 않습니다."));
        return numberWord.word;
    }
}
