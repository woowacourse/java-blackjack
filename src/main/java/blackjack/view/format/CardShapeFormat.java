package blackjack.view.format;

import blackjack.domain.card.CardShape;
import java.util.Arrays;

public enum CardShapeFormat {
    CLOVER("클로버"),
    DIA("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트");

    private final CardShape shape;
    private final String format;

    CardShapeFormat(final String format) {
        this.shape = CardShape.valueOf(name());
        this.format = format;
    }

    public static CardShapeFormat from(final CardShape shape) {
        return Arrays.stream(values())
                .filter(cardShapeFormat -> cardShapeFormat.shape == shape)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 카드 문양입니다."));
    }
    public String getFormat() {
        return format;
    }
}
