package BlackJack.domain;

import java.util.Arrays;
import java.util.List;

public enum Number {
    ACE("A", 1, 11),
    TWO("2",2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10",10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private String denomination;
    private int value;
    private int otherValue;

    Number(String denomination, int value, int otherValue){
        this.denomination = denomination;
        this.value = value;
        this.otherValue = otherValue;
    }

    Number(String denomination, int value){
        this.denomination = denomination;
        this.value = value;
    }

    public String getDenomination() {
        return denomination;
    }

    public int getValue() {
        return value;
    }

    public int getOtherValue() {
        return otherValue;
    }
}
