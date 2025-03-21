package card;

public enum NormalRank implements Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    JACK(10),
    QUEEN(10);

    private final int score;

    NormalRank(int score) {
        this.score = score;
    }

    @Override
    public boolean matches(Rank rank) {
        return this == rank;
    }

    @Override
    public int getScore() {
        return score;
    }
}
