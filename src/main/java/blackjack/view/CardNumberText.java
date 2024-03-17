package blackjack.view;

import blackjack.model.cards.CardNumber;
import java.util.Arrays;

public enum CardNumberText {
    ACE(CardNumber.ACE, "A"),
    TWO(CardNumber.TWO, "2"),
    THREE(CardNumber.THREE, "3"),
    FOUR(CardNumber.FOUR, "4"),
    FIVE(CardNumber.FIVE, "5"),
    SIX(CardNumber.SIX, "6"),
    SEVEN(CardNumber.SEVEN, "7"),
    EIGHT(CardNumber.EIGHT, "8"),
    NINE(CardNumber.NINE, "9"),
    TEN(CardNumber.TEN, "10"),
    JACK(CardNumber.JACK, "J"),
    QUEEN(CardNumber.QUEEN, "Q"),
    KING(CardNumber.KING, "K");

    private final CardNumber cardNumber;
    private final String text;

    CardNumberText(final CardNumber cardNumber, final String text) {
        this.cardNumber = cardNumber;
        this.text = text;
    }

    public static CardNumberText from(final CardNumber cardNumber) {
        return Arrays.stream(values())
                .filter(text -> text.cardNumber.equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 카드 숫자입니다."));
    }

    public String getText() {
        return text;
    }
}
