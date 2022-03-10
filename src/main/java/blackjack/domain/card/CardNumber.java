package blackjack.domain.card;

import static blackjack.domain.card.Cards.BLACK_JACK_NUMBER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        final int bonusMaxScore = countAce(numbers) * 10;
        final int defaultScore = sumDefaultScore(numbers);
        final int startScore = defaultScore + bonusMaxScore;

        return calculateScore(numbers, defaultScore, startScore);
    }

    private static int calculateScore(final List<CardNumber> numbers, final int defaultScore, final int startScore) {
        return IntStream.range(0, countAce(numbers))
                .map(aceCount -> decreaseByAceCount(startScore, aceCount))
                .filter(CardNumber::isLowerThanBlackJack)
                .findFirst()
                .orElse(defaultScore);
    }

    private static int countAce(final List<CardNumber> numbers) {
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

    private static boolean isLowerThanBlackJack(final int sumCount) {
        return sumCount <= BLACK_JACK_NUMBER;
    }

    public String getPrintValue() {
        return printValue;
    }
}
