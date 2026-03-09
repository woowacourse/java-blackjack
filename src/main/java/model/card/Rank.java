package model.card;

import java.util.Arrays;

public enum Rank {
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

    private final int value;
    private final String name;

    Rank(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Rank findByValue(int targetValue) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == targetValue)
                .findFirst()
                .orElseThrow();
    }

    public static Rank findByName(String name) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.name.equals(name))
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
