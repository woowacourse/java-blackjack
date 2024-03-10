package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardRank {

    ACE(1, 11),
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
    private final int specialScore;

    CardRank(int score) {
        this.score = score;
        this.specialScore = score;
    }

    CardRank(int score, int specialScore) {
        this.score = score;
        this.specialScore = specialScore;
    }

    public static List<CardRank> getAllCardRanks() {
        return Arrays.stream(values()).toList();
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getScore() {
        return score;
    }

    public int getSpecialScore() {
        return specialScore;
    }
}
