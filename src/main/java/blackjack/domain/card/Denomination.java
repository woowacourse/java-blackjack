package blackjack.domain.card;

import static blackjack.domain.state.Blackjack.BLACKJACK_TARGET_NUMBER;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static int calculateCardScore(final List<Card> cards) {
        return calculateScore(denominationsToCards(cards));
    }

    private static List<Denomination> denominationsToCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getDenomination)
                .collect(Collectors.toList());
    }

    private static int calculateScore(final List<Denomination> numbers) {
        final int bonusMaxScore = calculateAceCount(numbers) * ACE_BONUS_VALUE;
        final int defaultScore = sumDefaultScore(numbers);
        final int startScore = defaultScore + bonusMaxScore;

        return calculateScore(numbers, defaultScore, startScore);
    }

    private static int calculateAceCount(final List<Denomination> denominations) {
        return (int) denominations.stream()
                .filter(denomination -> denomination == A)
                .count();
    }

    private static int sumDefaultScore(final List<Denomination> numbers) {
        return numbers.stream()
                .mapToInt(number -> number.defaultValue)
                .sum();
    }

    private static int calculateScore(final List<Denomination> numbers, final int defaultScore, final int startScore) {
        return IntStream.range(0, calculateAceCount(numbers))
                .map(aceCount -> decreaseByAceCount(startScore, aceCount))
                .filter(Denomination::isLowerThanBlackjackTargetNumber)
                .findFirst()
                .orElse(defaultScore);
    }

    private static int decreaseByAceCount(final int startScore, final int aceCount) {
        return startScore - aceCount * ACE_BONUS_VALUE;
    }

    private static boolean isLowerThanBlackjackTargetNumber(final int sumCount) {
        return sumCount <= BLACKJACK_TARGET_NUMBER;
    }

    public static int calculateCardMaxScore(final List<Card> cards) {
        return calculateMaxScore(denominationsToCards(cards));
    }

    private static int calculateMaxScore(final List<Denomination> denominations) {
        final int defaultScore = calculateDefaultScore(denominations);

        final int bonusMaxScore = calculateAceCount(denominations) * ACE_BONUS_VALUE;
        return defaultScore + bonusMaxScore;
    }

    private static int calculateDefaultScore(final List<Denomination> denominations) {
        return denominations.stream()
                .mapToInt(denomination -> denomination.defaultValue)
                .sum();
    }

    public String getPrintValue() {
        return printValue;
    }
}
