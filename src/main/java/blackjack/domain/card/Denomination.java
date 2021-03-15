package blackjack.domain.card;

public enum Denomination {
    ACE(1, "A"), TWO(2, "2"), THREE(3, "3"),
    FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"),
    SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"), QUEEN(10, "Q"), KING(10, "K");

    public static final int ACE_UPPER_VALUE = 11;

    private final int value;
    private final String initial;

    Denomination(int value, String initial) {
        this.value = value;
        this.initial = initial;
    }

    public int getValue() {
        return value;
    }

    public String getInitial() {
        return initial;
    }
}
