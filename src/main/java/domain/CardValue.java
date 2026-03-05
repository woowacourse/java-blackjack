package domain;

import java.util.Arrays;

public enum CardValue {
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
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    private final String value;
    private final int number;

    CardValue(String value, int number) {
        this.value = value;
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public String getValue() {
        return this.value;
    }

    public static CardValue of(final String value){
        return Arrays.stream(values())
                .filter(val -> val.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("생성할 수 없는 value 입니다."));
    }
}