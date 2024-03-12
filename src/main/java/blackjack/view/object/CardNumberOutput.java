package blackjack.view.object;

import blackjack.domain.card.CardNumber;
import java.util.Arrays;

public enum CardNumberOutput {

    ACE(CardNumber.ACE ,"A"),
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
    private final String output;

    CardNumberOutput(CardNumber cardNumber, String output) {
        this.cardNumber = cardNumber;
        this.output = output;
    }

    public static String convertNumberToOutput(CardNumber cardNumber) {
        return Arrays.stream(values())
                .filter(cardNumberOutput -> cardNumberOutput.cardNumber == cardNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드 숫자입니다."))
                .output;
    }
}
