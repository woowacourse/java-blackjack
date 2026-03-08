package domain.card;

import domain.exception.OutOfBoundException;
import java.util.Arrays;

public enum CardNumber {
    ACE(11, "A"),
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

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 10;

    private int number;
    private String court;

    CardNumber(int number, String court) {
        this.number = number;
        this.court = court;
    }

    public static CardNumber matchCardNumber(String value) {
        validate(value);
        return Arrays.stream(CardNumber.values())
                .filter(card -> card.court.equals(value))
                .findAny()
                .orElseThrow(NoSuchFieldError::new);
    }

    private static void validate(String value) {
        try {
            int number = Integer.parseInt(value);
            validateRange(number);
        } catch (NumberFormatException e) {
            validateCourt(value);
        }
    }

    private static void validateRange(int number) {
        if (number < LOWER_BOUND || number > UPPER_BOUND) {
            throw new OutOfBoundException();
        }
    }

    private static void validateCourt(String value) {
        boolean isValid = Arrays.stream(CardNumber.values())
                .anyMatch(card -> card.court.equals(value));
        if (!isValid) {
            throw new IllegalArgumentException();
        }
    }

    public int getValue() {
        return number;
    }

    public String getCourt() {
        return court;
    }
}
