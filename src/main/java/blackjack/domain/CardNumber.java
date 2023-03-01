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

    private final int value;

    CardNumber(final int value) {
        this.value = value;
    }

    public static int getMaxValueNear21(final List<CardNumber> numbers) {
        final int aceCount = (int) numbers.stream()
                .filter(number -> number == ACE)
                .count();

        final int sumBeforeOptimize = numbers.stream()
                .mapToInt(number -> number.value)
                .sum();

        return IntStream.range(0, aceCount)
                .reduce(sumBeforeOptimize, (before, after) -> optimizeMaxValue(before));
    }

    private static int optimizeMaxValue(final int before) {
        if (before + 10 <= 21) {
            return before + 10;
        }
        return before;
    }

    public int getValue() {
        return value;
    }
}
