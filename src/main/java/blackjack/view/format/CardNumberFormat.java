package blackjack.view.format;

import blackjack.domain.card.CardNumber;
import java.util.Arrays;

public enum CardNumberFormat {

    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final CardNumber number;
    private final String format;

    CardNumberFormat(final String format) {
        this.number = CardNumber.valueOf(name());
        this.format = format;
    }

    public static CardNumberFormat from(final CardNumber number) {
        return Arrays.stream(values())
                .filter(cardNumberFormat -> cardNumberFormat.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 카드 넘버 입니다."));
    }

    public String getFormat() {
        return format;
    }
}
