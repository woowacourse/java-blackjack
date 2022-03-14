package blackjack.domain.card;

import java.util.List;

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
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private static final int SUM_HIDDEN_ACE = -1 + 11;
    private static final int BLACK_JACK_NUMBER = 21;

    private final int value;
    private final String name;

    CardNumber(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static int sum(List<CardNumber> cardNumbers) {
        return calculateBestNumber(sumTotal(cardNumbers), countAce(cardNumbers));
    }

    public String getName() {
        return name;
    }

    private static int sumTotal(List<CardNumber> cardNumbers) {
        return cardNumbers.stream()
            .mapToInt(number -> number.value)
            .sum();
    }

    private static long countAce(List<CardNumber> cardNumbers) {
        return cardNumbers.stream()
            .filter(number -> number == ACE)
            .count();
    }

    private static int calculateBestNumber(int total, long aceCount) {
        for (int i = 0; i < aceCount; i++) {
            total = sumUnderBlackJackNumber(total);
        }
        return total;
    }

    private static int sumUnderBlackJackNumber(int total) {
        if (total + SUM_HIDDEN_ACE <= BLACK_JACK_NUMBER) {
            total = total + SUM_HIDDEN_ACE;
        }
        return total;
    }
}
