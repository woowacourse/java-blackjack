public enum CardNumber {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    JACK(10),
    QUEEN(10),
    KING(10),
    ;

    private final int number;

    CardNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
