package blackjack.view.card;

import blackjack.domain.card.CardNumber;
import java.util.Arrays;

public enum CardNumberOutput {

    ACE("A"),
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
    ;

    private static final String NO_CARD_ERROR_MESSAGE = "카드가 없습니다";

    private final String output;

    CardNumberOutput(String output) {
        this.output = output;
    }

    public static String getOutput(CardNumber cardNumber) {
        return Arrays.stream(CardNumberOutput.values())
                .filter(cardNumberOutput -> cardNumberOutput.name().equals(cardNumber.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_CARD_ERROR_MESSAGE))
                .output;
    }
}
