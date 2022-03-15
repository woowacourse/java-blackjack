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

    private static final int BLACKJACK_NUMBER = 21;
    private static final int SUM_HIDDEN_ACE = 10;

    private final int value;
    private final String name;

    CardNumber(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static int getOptimizeTotalNumber(List<CardNumber> cardNumbers) {
        int totalNumber = getTotalNumber(cardNumbers);
        boolean containAce = isContainAce(cardNumbers);
        if (containAce && isBust(totalNumber + SUM_HIDDEN_ACE)) {
            return totalNumber + SUM_HIDDEN_ACE;
        }
        return totalNumber;
    }

    private static int getTotalNumber(List<CardNumber> cardNumbers) {
        return cardNumbers.stream()
            .mapToInt(number -> number.value)
            .sum();
    }

    private static boolean isContainAce(List<CardNumber> cardNumbers) {
        return cardNumbers.stream()
            .anyMatch(cardNumber -> cardNumber == ACE);
    }

    public static boolean isBust(int totalNumber) {
        return totalNumber <= BLACKJACK_NUMBER;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
