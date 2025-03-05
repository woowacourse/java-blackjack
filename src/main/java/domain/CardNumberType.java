package domain;

import static util.ExceptionConstants.*;

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

    private final List<Integer> cardNumbers;

    CardNumberType(int... cardNumbers) {
        this.cardNumbers = Arrays.stream(cardNumbers)
                .boxed()
                .toList();
    }

    public static int getAceLowNumber() {
        return ACE.cardNumbers.getFirst();
    }

    public static int getAceHighNumber() {
        return ACE.cardNumbers.getLast();
    }

    public int getDefaultNumber() {
        return cardNumbers.getFirst();
    }

    public static CardNumberType findByRandom(int randomIndex) {
        if(randomIndex >= CardNumberType.values().length) {
            throw new IllegalArgumentException(ERROR_HEADER + "해당하는 카드 숫자의 인덱스가 존재하지 않습니다.");
        }
        return CardNumberType.values()[randomIndex];
    }

    public boolean isAce() {
        return this == ACE;
    }
}
