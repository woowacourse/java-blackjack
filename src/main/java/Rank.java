public enum Rank {
    ACE(1),
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
    ;

    private final Integer score;

    Rank(Integer score) {
        this.score = score;
    }

    public static boolean isFaceCard(Rank rank) {
        return rank.equals(JACK) || rank.equals(QUEEN) || rank.equals(KING);
    }

    public static boolean isAce(Rank rank) {
        return rank.equals(ACE);
    }

    public Integer getScore() {
        return score;
    }
}
