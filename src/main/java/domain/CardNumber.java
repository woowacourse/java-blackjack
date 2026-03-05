package domain;

import domain.exception.OutOfBoundException;
import java.util.Arrays;

public enum CardNumber {
    A(11, "A"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    J(10, "J"),
    Q(10, "Q"),
    K(10, "K");

    private int number;
    private String court;

    CardNumber(int number, String court) {
        this.number = number;
        this.court = court;
    }

    public void softHand() {
        this.number = ONE.number;
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
        if (number < 1 || number > 10) {
            throw new OutOfBoundException();
        }
    }

    private static void validateCourt(String value) {
        CardNumber.valueOf(value);
    }

    public int getValue() {
        return number;
    }
}
