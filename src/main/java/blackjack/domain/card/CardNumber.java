package blackjack.domain.card;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum CardNumber {

    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    J("J", 10),
    K("K", 10),
    Q("Q", 10),
    A("A", 11);

    private final String number;
    private final int value;

    CardNumber(String number, int value) {
        this.number = number;
        this.value = value;
    }

    public String getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }
}
