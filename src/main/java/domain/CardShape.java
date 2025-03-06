package domain;

import java.util.Arrays;

public enum CardShape {
    DIA(1),
    HEART(2),
    SPADE(3),
    CLOVER(4),
    ;

    private final int index;

    CardShape(int index) {
        this.index = index;
    }

    public static CardShape pickCardShape(int index) {
        Arrays.stream(values())
                .filter((value) -> value.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드 모양 범위 내에서 선택해주세요 (1~4)"));
    }
}
