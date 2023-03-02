package domain.type;

public enum Letter {
    ACE(11, "A"),
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
    JACK(10, "J");

    private final int number;
    private final String sign;

    Letter(final int number, final String sign) {
        this.number = number;
        this.sign = sign;
    }

    public int getNumber() {
        return number;
    }

    public String getSign() {
        return sign;
    }
}
