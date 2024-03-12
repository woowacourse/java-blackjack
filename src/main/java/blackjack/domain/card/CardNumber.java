package blackjack.domain.card;

import java.util.Arrays;
import java.util.stream.Stream;

public enum CardNumber {
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
    ACE(11);

    static final int ACE_BONUS_NUMBER = 1;
    private final int number;

    CardNumber(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Stream<CardNumber> stream() {
        return Arrays.stream(values());
    }
}
