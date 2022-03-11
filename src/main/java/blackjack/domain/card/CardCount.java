package blackjack.domain.card;

import java.util.Arrays;

public enum CardCount {

    ZERO(0, "영"),
    ONE(1, "한"),
    TWO(2, "두"),
    THREE(3, "세"),
    FOUR(4, "네"),
    FIVE(5, "다섯");

    private final int value;
    private final String name;

    CardCount(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static CardCount of(int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }

    public boolean isDraw() {
        return this == ZERO;
    }

    public String getName() {
        return name;
    }
}
