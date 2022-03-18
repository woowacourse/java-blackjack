package blackjack.domain.card;

import blackjack.domain.BlackjackGame;

import java.util.List;

public enum Number {
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

    private static final int SPECIAL_ACE = 11;

    private final int value;
    private final String name;

    Number(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static int sum(List<Number> numbers) {
        return calculateBestNumber(sumTotal(numbers), countAce(numbers));
    }

    public String getName() {
        return name;
    }

    private static int sumTotal(List<Number> numbers) {
        return numbers.stream()
                .mapToInt(number -> number.value)
                .sum();
    }

    private static long countAce(List<Number> numbers) {
        return numbers.stream()
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
        if (total + SPECIAL_ACE - ACE.value <= BlackjackGame.BLACKJACK_NUMBER) {
            total = total + SPECIAL_ACE - ACE.value;
        }
        return total;
    }
}
