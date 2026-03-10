package domain.model;

import java.util.Arrays;

import static constant.ErrorMessage.INVALID_CARD_SHAPE_CODE;

public enum CardShape {
    HEART(1, "하트"),
    SPADE(2, "스페이드"),
    DIAMOND(3, "다이어몬드"),
    CLUB(4, "클로버");

    private final int code;
    private final String name;

    CardShape(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CardShape getShape(int code) {
        return Arrays.stream(values())
                .filter(val -> code == val.code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_SHAPE_CODE.getMessage()));
    }

    public String getName() {
        return name;
    }
}
