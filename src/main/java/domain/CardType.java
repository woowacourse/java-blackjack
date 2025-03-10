package domain;

import static util.ExceptionConstants.*;

public enum CardType {
    SPACE,
    HEART,
    CLOVER,
    DIAMOND;

    private static final String INVALID_INDEX = "해당하는 카드 모양의 인덱스가 존재하지 않습니다.";

    public static CardType findByRandomIndex(int randomIndex) {
        validateIndex(randomIndex);
        return CardType.values()[randomIndex];
    }

    private static void validateIndex(int index) {
        if (index >= CardNumberType.values().length) {
            throw new IllegalArgumentException(ERROR_HEADER + INVALID_INDEX);
        }
    }
}
