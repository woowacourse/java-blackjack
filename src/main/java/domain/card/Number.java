package domain.card;

import java.util.Arrays;
import java.util.List;

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
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int numericValue;
    private final String faceValue;

    Number(int numericValue, String faceValue) {
        this.numericValue = numericValue;
        this.faceValue = faceValue;
    }

    public int getNumericValue() {
        return numericValue;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public static List<Number> getAllNumbers() {
        return Arrays.stream(values())
                .toList();
    }
}
