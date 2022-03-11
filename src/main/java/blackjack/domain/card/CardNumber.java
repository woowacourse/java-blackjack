package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardNumber {

    A(1, "A"),
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
    KING(10, "K"),
    ;

    private static final int MIN_VALUE_ADDING_BONUS = 11;
    private static final int BONUS_VALUE = 10;

    private final int defaultValue;
    private final String printValue;

    CardNumber(final int defaultValue, final String printValue) {
        this.defaultValue = defaultValue;
        this.printValue = printValue;
    }

    public static List<CardNumber> allNumbers() {
        return Arrays.asList(values());
    }

    public static int calculateScore(final List<CardNumber> numbers) {
        final int defaultSum = calculateDefaultSum(numbers);
        final int countOfA = countA(numbers);
        if (countOfA > 0) {
            return addBonusValue(defaultSum, countOfA);
        }
        return defaultSum;
    }

    private static int calculateDefaultSum(final List<CardNumber> numbers) {
        return numbers.stream()
                .mapToInt(number -> number.defaultValue)
                .sum();
    }

    private static int countA(final List<CardNumber> numbers) {
        return (int) numbers.stream()
                .filter(number -> number == A)
                .count();
    }

    private static int addBonusValue(final int defaultSum, int countOfA) {
        int bonusSum = defaultSum;
        while (bonusSum <= MIN_VALUE_ADDING_BONUS && countOfA-- > 0) {
            bonusSum += BONUS_VALUE;
        }
        return bonusSum;
    }

    public String getPrintValue() {
        return printValue;
    }
}
