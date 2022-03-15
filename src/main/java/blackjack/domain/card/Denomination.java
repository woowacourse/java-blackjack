package blackjack.domain.card;

import java.util.List;

public enum Denomination {

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

    private static final int ACE_BONUS_VALUE = 10;

    private final int defaultValue;
    private final String printValue;

    Denomination(final int defaultValue, final String printValue) {
        this.defaultValue = defaultValue;
        this.printValue = printValue;
    }

    public static boolean canBust(final List<Denomination> denominations) {
        final int defaultSum = calculateDefaultValuesSum(denominations);

        return calculateMaxSum(denominations, defaultSum) > 21;
    }

    private static int calculateDefaultValuesSum(final List<Denomination> denominations) {
        return denominations.stream()
                .mapToInt(denomination -> denomination.defaultValue)
                .sum();
    }

    private static int calculateMaxSum(final List<Denomination> denominations, final int defaultSum) {
        return defaultSum + calculateAceCount(denominations) * ACE_BONUS_VALUE;
    }

    private static int calculateAceCount(final List<Denomination> denominations) {
        return (int) denominations.stream()
                .filter(denomination -> denomination == A)
                .count();
    }
}
