package blackjack.domain.card;

public enum Denomination {

    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J"),
    ACE(11, "A");

    private static final int ACE_ADJUST_UNIT = 1;

    private final int point;
    private final String name;

    Denomination(final int point, final String name) {
        this.point = point;
        this.name = name;
    }

    public static int adjustAce(final int point) {
        return point - (ACE.getPoint() - ACE_ADJUST_UNIT);
    }

    public int getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }
}
