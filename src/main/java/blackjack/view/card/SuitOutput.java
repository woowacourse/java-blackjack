package blackjack.view.card;

import blackjack.domain.card.Suit;
import java.util.Arrays;

public enum SuitOutput {

    SPADE("스페이드"),
    CLUB("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    ;

    private static final String NO_SUIT_ERROR_MESSAGE = "모양이 없습니다";

    private final String output;

    SuitOutput(String output) {
        this.output = output;
    }

    public static String getOutput(Suit suit) {
        return Arrays.stream(SuitOutput.values())
                .filter(suitOutput -> suitOutput.name().equals(suit.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUIT_ERROR_MESSAGE))
                .output;
    }
}
