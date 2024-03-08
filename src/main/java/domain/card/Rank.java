package domain.card;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    SMALL_ACE("A", 1),
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
    BIG_ACE("A", 11);

    private final String name;
    private final int score;

    Rank(String name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static List<Rank> getRanks() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != SMALL_ACE)
                .toList();
    }

    public boolean isAce() {
        return name().contains("ACE");
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
