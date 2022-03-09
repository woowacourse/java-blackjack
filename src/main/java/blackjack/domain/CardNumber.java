package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public enum CardNumber {

    A(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ;

    private final int defaultValue;

    CardNumber(final int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public static List<CardNumber> cardNumbers() {
        return Arrays.asList(values());
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public static int calculateScore(final List<CardNumber> numbers) {
        final int bonusMaxScore = calculateAceCount(numbers) * 10;
        final int defaultScore = sumDefaultScore(numbers);
        final int startScore = defaultScore + bonusMaxScore;

        return IntStream.range(0, calculateAceCount(numbers))
                .map(aceCount -> decreaseByAceCount(startScore, aceCount))
                .filter(CardNumber::filterBlackJack)
                .findFirst()
                .orElse(defaultScore);
    }

    private static int calculateAceCount(final List<CardNumber> numbers) {
        return (int) numbers.stream()
                .filter(number -> number == A)
                .count();
    }

    private static int sumDefaultScore(final List<CardNumber> numbers) {
        return numbers.stream()
                .mapToInt(number -> number.defaultValue)
                .sum();
    }

    private static int decreaseByAceCount(final int startScore, final int aceCount) {
        return startScore - aceCount * 10;
    }

    private static boolean filterBlackJack(final int sumCount) {
        return sumCount <= 21;
    }
}
