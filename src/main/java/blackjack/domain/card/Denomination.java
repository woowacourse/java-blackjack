package blackjack.domain.card;

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

    private final int value;
    private final String printValue;

    Denomination(final int value, final String printValue) {
        this.value = value;
        this.printValue = printValue;
    }

    public static Score calculateCardScore(final List<Card> cards) {
        return calculateScore(denominationsToCards(cards));
    }

    private static List<Denomination> denominationsToCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getDenomination)
                .collect(Collectors.toList());
    }

    private static Score calculateScore(final List<Denomination> numbers) {
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
                .mapToInt(number -> number.value)
                .sum();
    }

    private static Score calculateScore(final List<Denomination> numbers, final int defaultScore, final int startScore) {
        return IntStream.range(0, calculateAceCount(numbers))
                .mapToObj(aceCount -> decreaseByAceCount(startScore, aceCount))
                .filter(score -> !score.isBustScore())
                .findFirst()
                .orElse(new Score(defaultScore));
    }

    private static Score decreaseByAceCount(final int startScore, final int aceCount) {
        return new Score(startScore - aceCount * ACE_BONUS_VALUE);
    }

    public static Score calculateCardMaxScore(final List<Card> cards) {
        return calculateMaxScore(denominationsToCards(cards));
    }

    private static Score calculateMaxScore(final List<Denomination> denominations) {
        final int defaultScore = calculateDefaultScore(denominations);

        final int bonusMaxScore = calculateAceCount(denominations) * ACE_BONUS_VALUE;
        return new Score(defaultScore + bonusMaxScore);
    }

    private static int calculateDefaultScore(final List<Denomination> denominations) {
        return denominations.stream()
                .mapToInt(denomination -> denomination.value)
                .sum();
    }

    public String getPrintValue() {
        return printValue;
    }
}
