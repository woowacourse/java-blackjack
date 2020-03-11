package domain.card;

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
    JACK(10),
    QUEEN(11),
    KING(12);

    private static final String NO_EXIST_MESSAGE = "적절한 숫자의 랭크가 존재하지 않습니다.";
    private static final int ALPHABET_SCORE = 10;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(int value) {
        return Arrays.asList(Rank.values())
                .stream()
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_MESSAGE));
    }

    public int getScore() {
        if (this == Rank.KING || this == Rank.QUEEN || this == Rank.JACK) {
            return ALPHABET_SCORE;
        }

        return value;
    }

    public boolean isAce() {
        return this == Rank.ACE;
    }
}
