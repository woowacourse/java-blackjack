package second.domain.card;

import java.util.Arrays;

public enum Rank {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    J(10),
    Q(10),
    K(10);

    private static final String NO_EXIST_RANK_MESSAGE = "적절한 숫자의 랭크가 존재하지 않습니다.";

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank of(final int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_RANK_MESSAGE));
    }

    public boolean is(final Rank rank) {
        return this == rank;
    }

    public int getValue() {
        return value;
    }
}

