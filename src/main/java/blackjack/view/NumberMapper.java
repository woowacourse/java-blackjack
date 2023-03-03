package blackjack.view;

import blackjack.domain.Number;

import java.util.Arrays;

public enum NumberMapper {

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
    private final String value;

    NumberMapper(Number number, String value) {
        this.number = number;
        this.value = value;
    }

    public static String map(Number number) {
        return Arrays.stream(values())
                .filter(numberMapper -> numberMapper.number.equals(number))
                .map(NumberMapper::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 숫자가 없습니다."));
    }

    public String getValue() {
        return value;
    }
}
