package domain.model;

import java.util.Arrays;

import static constant.ErrorMessage.INVALID_CARD_RANK_CODE;

public enum CardRank {

    ACE(1, "A", 1, 11),
    TWO(2, "2", 2, 2),
    THREE(3, "3", 3, 3),
    FOUR(4, "4", 4, 4),
    FIVE(5, "5", 5, 5),
    SIX(6, "6", 6, 6),
    SEVEN(7, "7", 7, 7),
    EIGHT(8, "8", 8, 8),
    NINE(9, "9", 9, 9),
    TEN(10, "10", 10, 10),
    JACK(11, "J", 10, 10),
    QUEEN(12, "Q", 10, 10),
    KING(13, "K", 10, 10);

    private final int code;
    private final String name;
    private final int value;
    private final int additionalValue;

    CardRank(int code, String name, int value, int additionalValue) {
        this.code = code;
        this.name = name;
        this.value = value;
        this.additionalValue = additionalValue;
    }

    public static CardRank getRank(int code) {
        return Arrays.stream(values())
                .filter(value -> value.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_RANK_CODE.getMessage()));
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getAdditionalValue() {
        return additionalValue;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getAdditionalValue() {
        return additionalValue;
    }
}
