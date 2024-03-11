package blackjack.view.format;

import blackjack.domain.card.CardShape;
import java.util.Arrays;

public enum CardShapeFormat {
    CLOVER(CardShape.CLOVER.name(), "클로버"),
    DIA(CardShape.CLOVER.name(), "다이아몬드"),
    SPADE(CardShape.CLOVER.name(), "스페이드"),
    HEART(CardShape.CLOVER.name(), "하트");

    private final String signal;
    private final String format;

    CardShapeFormat(final String signal, final String format) {
        this.signal = signal;
        this.format = format;
    }

    public static CardShapeFormat of(final String inputSignal) {
        return Arrays.stream(values())
                .filter(value -> value.signal.equals(inputSignal))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카드 모양 형식입니다."));
    }

    public String getFormat() {
        return format;
    }
}
