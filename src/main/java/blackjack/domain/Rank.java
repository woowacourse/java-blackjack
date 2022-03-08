package blackjack.domain;

public enum Rank {
    ACE(1, true),
    TWO(2, false),
    THREE(3, false),
    FOUR(4, false),
    FIVE(5, false),
    SIX(6, false),
    SEVEN(7, false),
    EIGHT(8, false),
    NINE(9, false),
    TEN(10, false),
    JACK(10, false),
    QUEEN(10, false),
    KING(10, false)
    ;

    private final int point;
    private final boolean aceBonus;

    Rank(int point, boolean aceBonus) {
        this.point = point;
        this.aceBonus = aceBonus;
    }

    public int getPoint() {
        return point;
    }

    public boolean isAceBonus() {
        return aceBonus;
    }
}
