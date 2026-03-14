package domain.card.vo;

import domain.score.Score;

public enum Rank {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ;

    private final Score score;
    private final String displayName;

    Rank(int score, String displayName) {
        this.score = new Score(score);
        this.displayName = displayName;
    }

    public static boolean isAce(Rank rank) {
        return rank.equals(ACE);
    }

    public Score getScore() {
        return score;
    }

    public String getDisplayName() {
        return displayName;
    }
}
