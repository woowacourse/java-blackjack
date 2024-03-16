package domain.card;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    SMALL_ACE(1),
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
    KING(10),
    BIG_ACE(11);

    private final int score;

    Rank(final int score) {
        this.score = score;
    }

    public static List<Rank> getRanks() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != SMALL_ACE)
                .toList();
    }

    public static int selectAceScore(int score, int blackJackNumber) {
        if (isHardHand(score, blackJackNumber)) {
            return SMALL_ACE.score;
        }
        return BIG_ACE.score;
    }

    private static boolean isHardHand(final int score, final int blackJackNumber) {
        return score + BIG_ACE.score > blackJackNumber;
    }

    public boolean isAce() {
        return this == SMALL_ACE || this == BIG_ACE;
    }

    public int getScore() {
        return score;
    }
}
