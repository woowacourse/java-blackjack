package blackjack.domain;

import java.util.List;
import java.util.stream.IntStream;

public enum CardNumber {
    ACE(1, "A"),
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

    public static final int ACE_CONVERT_NUMBER = 10;
    private final int value;
    private final String number;

    CardNumber(final int value, final String number) {
        this.value = value;
        this.number = number;
    }

    public static int getMaxValueNearBlackJack(final List<CardNumber> numbers, final int blackJack) {
        final int aceCount = (int) numbers.stream()
                .filter(number -> number == ACE)
                .count();

        final int sumBeforeOptimize = numbers.stream()
                .mapToInt(number -> number.value)
                .sum();

        return IntStream.range(0, aceCount)
                .reduce(sumBeforeOptimize, (before, after) -> optimizeMaxValue(before, blackJack));
    }

    public String getNumber() {
        return number;
    }

    private static int optimizeMaxValue(final int before, final int blackJack) {
        if (before + ACE_CONVERT_NUMBER <= blackJack) {
            return before + ACE_CONVERT_NUMBER;
        }
        return before;
    }
}
