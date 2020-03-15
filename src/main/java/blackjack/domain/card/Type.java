package blackjack.domain.card;

public enum Type {
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
    KING(10, "K");

    private static final int ACE_CRITICAL_POINT = 11;
    private static final int ACE_UPPER_POINT = 11;
    private static final int ACE_LOWER_POINT = 1;

    private final int point;
    private final String name;

    Type(int point, String name) {
        this.point = point;
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public int getPointOfAceUsing(int score) {
        if (score > ACE_CRITICAL_POINT) {
            return ACE_LOWER_POINT;
        }
        return ACE_UPPER_POINT;
    }

    public String getName() {
        return name;
    }
}
