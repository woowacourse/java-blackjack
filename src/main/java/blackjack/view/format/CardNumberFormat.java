package blackjack.view.format;

import blackjack.domain.card.CardNumber;
import java.util.Arrays;

public enum CardNumberFormat {

    TWO(CardNumber.TWO,"2"),
    THREE(CardNumber.THREE,"3"),
    FOUR(CardNumber.FOUR,"4"),
    FIVE(CardNumber.FIVE,"5"),
    SIX(CardNumber.SIX,"6"),
    SEVEN(CardNumber.SEVEN,"7"),
    EIGHT(CardNumber.EIGHT,"8"),
    NINE(CardNumber.NINE,"9"),
    TEN(CardNumber.TEN,"10"),
    JACK(CardNumber.JACK,"J"),
    QUEEN(CardNumber.QUEEN,"Q"),
    KING(CardNumber.KING,"K"),
    ACE(CardNumber.ACE,"A");

    private final CardNumber number;
    private final String format;

    CardNumberFormat(CardNumber number, String format) {
        this.number = number;
        this.format = format;
    }

    public static CardNumberFormat from(CardNumber number) {
        return Arrays.stream(values())
                .filter(cardNumberFormat -> cardNumberFormat.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 카드 넘버 입니다."));
    }

    public String getFormat() {
        return format;
    }
}
