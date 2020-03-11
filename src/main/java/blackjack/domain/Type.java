package blackjack.domain;

public enum Type {
    ACE(1, "A"),
    TWO(2, "1"),
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
    KING(10, "K");

    private final int point;
    private final String simpleName;

    Type(int point, String simpleName) {
        this.point = point;
        this.simpleName = simpleName;
    }

    public int getPoint() {
        return point;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
