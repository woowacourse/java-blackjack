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

    private static final int BLACK_JACK = 21;
    private final String name;
    private final int score;

    Rank(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static List<Rank> getRanks() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != SMALL_ACE)
                .toList();
    }

    public boolean isAce() {
        return this == SMALL_ACE || this == BIG_ACE;
    }

    public static int selectAceScore(int score){
        if(score + BIG_ACE.score>BLACK_JACK){
            return SMALL_ACE.score;
        }
        return BIG_ACE.score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
