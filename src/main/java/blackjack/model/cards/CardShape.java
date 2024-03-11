package blackjack.model.cards;

import java.util.Arrays;

public enum CardShape {
    SPADE(1, "스페이드"),
    DIAMOND(2, "다이아몬드"),
    HEART(3, "하트"),
    CLOVER(4, "클로버");

    private final int order;
    private final String text;

    CardShape(int order, String text) {
        this.order = order;
        this.text = text;
    }

    public static CardShape generate(int number) {
        return Arrays.stream(values())
                .filter(cardShape -> cardShape.order == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드모양을 고르는 숫자가 올바르지 않습니다."));
    }

    public String getText() {
        return text;
    }
}
