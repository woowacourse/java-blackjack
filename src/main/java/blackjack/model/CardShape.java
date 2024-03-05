package blackjack.model;

import java.util.Arrays;

public enum CardShape {
    SPADE(1),
    DIAMOND(2),
    HEART(3),
    CLOVER(4);

    private final int order;

    CardShape(int order) {
        this.order = order;
    }

    public static CardShape generate(int number) {
        return Arrays.stream(values())
                .filter(cardShape -> cardShape.order == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드모양을 고르는 숫자가 올바르지 않습니다."));
    }
}
