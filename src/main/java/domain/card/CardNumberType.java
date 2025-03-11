package domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardNumberType {
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
    JACK(10),
    QUEEN(10),
    KING(10);

    private static final int ACE_HIGH_CONVERSION_THRESHOLD = 10;

    private final List<Integer> cardNumbers;

    CardNumberType(int... cardNumbers) {
        this.cardNumbers = Arrays.stream(cardNumbers)
                .boxed()
                .toList();
    }

    public static int getAceNumber(int restSum) {
        if (restSum <= ACE_HIGH_CONVERSION_THRESHOLD) {
            return CardNumberType.getAceHighNumber();
        }
        return CardNumberType.getAceLowNumber();
    }

    public int getDefaultNumber() {
        return cardNumbers.getFirst();
    }

    private static int getAceHighNumber() {
        return ACE.cardNumbers.getLast();
    }

    private static int getAceLowNumber() {
        return ACE.cardNumbers.getFirst();
    }
}
