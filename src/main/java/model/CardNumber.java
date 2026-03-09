package model;

import java.util.Arrays;

public enum CardNumber {
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
    K(10, "K"),
    ACE(11, "A");

    private int value;
    private String name;

    CardNumber(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static CardNumber findByValue(int targetValue) {
        return Arrays.stream(CardNumber.values())
                .filter(cardNumber -> cardNumber.value == targetValue)
                .findFirst()
                .orElseThrow();
    }

    public static CardNumber findByName(String name) {
        return Arrays.stream(CardNumber.values())
                .filter(cardNumber -> cardNumber.name.equals(name))
                .findFirst()
                .orElseThrow();
    }

    public int toValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
