package domain;

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
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int score;

    Rank(final int score) {
        this.score = score;
    }

    public static Rank findBy(final int index) {
        return Arrays.stream(Rank.values())
                .filter(number -> number.ordinal() == index)
                .findFirst()
                .orElseThrow();
    }

    public int getScore() {
        return score;
    }
}
