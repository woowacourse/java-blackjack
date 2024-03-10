package domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
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
    KING("K", 10),
    ACE("A", 11);

    private final String text;
    private final int value;

    Rank(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static List<Rank> getValues() {
        return Arrays.stream(Rank.values())
                .toList();
    }

    public boolean isAce() {
        return this == ACE;
    }

    public String getText() {
        return this.text;
    }

    public int getValue() {
        return this.value;
    }
}
