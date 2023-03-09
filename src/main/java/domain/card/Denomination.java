package domain.card;

import java.util.Arrays;

public enum Denomination {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String name;
    private final int value;

    Denomination(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static int convertNumber(String name) {
        return Arrays.stream(values())
                .filter(card -> card.name.equals(name))
                .map(card -> card.value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 카드 형식이 아닙니다."));
    }
}
