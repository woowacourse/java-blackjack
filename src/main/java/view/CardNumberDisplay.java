package view;

import card.Number;

import java.util.Arrays;

public enum CardNumberDisplay {

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
    JACK(Number.JACK, "J"),
    QUEEN(Number.QUEEN, "Q"),
    KING(Number.KING, "K");

    private final Number number;
    private final String display;

    CardNumberDisplay(Number number, String display) {
        this.number = number;
        this.display = display;
    }

    public static CardNumberDisplay fromNumber(Number number) {
        return Arrays.stream(CardNumberDisplay.values())
                .filter(displayNumber -> displayNumber.number == number)
                .findFirst()
                .orElseThrow();
    }
}
