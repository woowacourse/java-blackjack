package blackjack.domain;

public enum Denomination {

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
    QUEEN(10),
    JACK(10),
    ACE(11);

    private final int point;

    Denomination(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
