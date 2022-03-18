package blackjack.model.card;

import java.util.List;

public enum CardNumber {

    ACE(1, "ACE"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "JACK"),
    QUEEN(10, "QUEEN"),
    KING(10, "KING"),
    ;

    public static final int ADVANTAGE_ADD_SCORE_LIMIT = 11;
    public static final int ACE_ADVANTAGE_NUMBER = 10;

    private final int baseValue;
    private final String valueForPrint;

    CardNumber(final int baseValue, final String valueForPrint) {
        this.baseValue = baseValue;
        this.valueForPrint = valueForPrint;
    }

    public String getValueForPrint() {
        return valueForPrint;
    }

    public static int calculateScore(final List<CardNumber> cardNumbers) {
        int score = calculateBaseScore(cardNumbers);
        if (hasAceIn(cardNumbers)) {
            return addAdvantageTo(score);
        }
        return score;
    }

    private static int calculateBaseScore(final List<CardNumber> cardNumbers) {
        int score = 0;
        for (CardNumber number : cardNumbers) {
            score += number.baseValue;
        }
        return score;
    }

    private static boolean hasAceIn(final List<CardNumber> cardNumbers) {
        return cardNumbers.stream()
                .anyMatch(number -> number == ACE);
    }

    private static int addAdvantageTo(final int score) {
        if (score <= ADVANTAGE_ADD_SCORE_LIMIT) {
            return score + ACE_ADVANTAGE_NUMBER;
        }
        return score;
    }
}
