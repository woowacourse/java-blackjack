package blackjack.view.format;

import blackjack.domain.card.CardShape;
import java.util.Arrays;

public enum CardShapeFormat {
    CLOVER(CardShape.CLOVER, "클로버"),
    DIA(CardShape.DIA, "다이아몬드"),
    SPADE(CardShape.SPADE, "스페이드"),
    HEART(CardShape.HEART, "하트");

    private final CardShape shape;
    private final String format;

    CardShapeFormat(CardShape shape, String format) {
        this.shape = shape;
        this.format = format;
    }

    public static CardShapeFormat from(CardShape shape) {
        return Arrays.stream(values())
                .filter(cardShapeFormat -> cardShapeFormat.shape == shape)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 카드 문양입니다."));
    }
    public String getFormat() {
        return format;
    }
}
