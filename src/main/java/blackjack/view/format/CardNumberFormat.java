package blackjack.view.format;

import blackjack.domain.card.CardNumber;
import java.util.Arrays;

public enum CardNumberFormat {

    TWO(CardNumber.TWO.name(), "2"),
    THREE(CardNumber.THREE.name(), "3"),
    FOUR(CardNumber.FOUR.name(), "4"),
    FIVE(CardNumber.FIVE.name(), "5"),
    SIX(CardNumber.SIX.name(), "6"),
    SEVEN(CardNumber.SEVEN.name(), "7"),
    EIGHT(CardNumber.EIGHT.name(), "8"),
    NINE(CardNumber.NINE.name(), "9"),
    TEN(CardNumber.TEN.name(), "10"),
    JACK(CardNumber.JACK.name(), "J"),
    QUEEN(CardNumber.QUEEN.name(), "Q"),
    KING(CardNumber.KING.name(), "K"),
    ACE(CardNumber.ACE.name(), "A");

    private final String signal;
    private final String format;

    CardNumberFormat(final String signal, final String format) {
        this.signal = signal;
        this.format = format;
    }

    public static CardNumberFormat of(final String inputSignal) {
        return Arrays.stream(values())
                .filter(value -> value.signal.equals(inputSignal))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카드 숫자 형식입니다."));
    }

    public String getFormat() {
        return format;
    }
}
