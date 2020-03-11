package domain.card;

import java.util.Arrays;

public enum Symbol {
    ACE(1, "A"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int point;
    private final String alias;

    Symbol(final int point) {
        this(point, String.valueOf(point));
    }

    Symbol(final int point, final String alias) {
        this.point = point;
        this.alias = alias;
    }

    public static Symbol of(final int point) {
        return Arrays.stream(values())
                .filter(type -> type.point == point)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 심볼입니다."));
    }

    public static Symbol of(final String alias) {
        return Arrays.stream(values())
                .filter(type -> type.alias.equals(alias))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 심볼입니다."));
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getPoint() {
        return point;
    }

    public String getAlias() {
        return alias;
    }
}
