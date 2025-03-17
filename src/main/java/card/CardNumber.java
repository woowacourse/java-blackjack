package card;

import java.util.List;

public enum CardNumber {

    ACE(1, 11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    QUEEN(10),
    JACK(10),
    ;

    private final List<Integer> values;

    CardNumber(List<Integer> values) {
        this.values = values;
    }

    CardNumber(int value) {
        this(List.of(value));
    }

    CardNumber(int value1, int value2) {
        this(List.of(value1, value2));
    }

    public boolean isSame(CardNumber cardNumber) {
        return this.equals(cardNumber);
    }

    public int getValue() {
        return values.getFirst();
    }
}
