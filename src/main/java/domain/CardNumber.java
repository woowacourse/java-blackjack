package domain;

import java.util.List;

public enum CardNumber {

    ACE(11),
    ACE_ANOTHER(1),
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
    KING(10);

    private final int number;

    CardNumber(int number) {
        this.number = number;
    }

    public static List<CardNumber> getHonorCard() {
        return List.of(ACE, JACK, QUEEN, KING);
    }

    public int getNumber() {
        return number;
    }
}
