package domain;

public enum Rank {
    ACE(1, "A"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"),
    SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"),
    JACK(10, "J"), KING(10, "K"), QUEEN(10, "Q");

    private final int score;
    private final String rankName;

    Rank(int score, String rankName) {
        this.score = score;
        this.rankName = rankName;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getScore() {
        return score;
    }

    public String getRankName() {
        return rankName;
    }
}
