package domain.card;

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

    public static final int ACE_HIGH_NUMBER = 11;
    public static final int ACE_LOW_HIGH_GAP = ACE_HIGH_NUMBER - ACE.number();

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    int number() {
        return number;
    }
}
