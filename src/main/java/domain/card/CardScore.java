package domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardScore {
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

    private final int value;
    private final String symbol;

    CardScore(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public static List<CardScore> getAllCardScores() {
        return Arrays.stream(CardScore.values())
                .toList();
    }

    public int getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }
}
