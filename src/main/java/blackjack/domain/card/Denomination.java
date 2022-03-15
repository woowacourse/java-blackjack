package blackjack.domain.card;

import java.util.Arrays;

public enum Denomination {
    ACE("A", 11),
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
    
    private final String initial;
    private final int point;
    
    Denomination(final String initial, final int point) {
        this.initial = initial;
        this.point = point;
    }
    
    public static Denomination of(final String initialInput) {
        return Arrays.stream(Denomination.values())
                .filter(value -> value.initial.equals(initialInput))
                .findAny()
                .orElseThrow();
    }
    
    public String getInitial() {
        return initial;
    }
    
    public int getPoint() {
        return point;
    }
}
