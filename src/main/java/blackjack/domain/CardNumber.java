package blackjack.domain;

import java.util.List;
import java.util.stream.IntStream;

public enum CardNumber {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    QUEEN(10),
    JACK(10);

    public static final int ACE_CONVERT_NUMBER = 10;
    private final int value;

    CardNumber(final int value) {
        this.value = value;
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

    public int getValue() {
        return value;
    }

    private static int optimizeMaxValue(final int before, final int blackJack) {
        if (before + ACE_CONVERT_NUMBER <= blackJack) {
            return before + ACE_CONVERT_NUMBER;
        }
        return before;
    }
}
