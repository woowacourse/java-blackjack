package blackjack.view.label;

import blackjack.model.card.Suit;
import java.util.Arrays;

public enum SuitLabel {
    DIAMOND(Suit.DIAMOND, "다이아몬드"),
    CLUB(Suit.CLUB, "클로버"),
    SPADE(Suit.SPADE, "스페이드"),
    HEART(Suit.HEART, "하트");

    private final Suit suit;
    private final String label;

    SuitLabel(Suit suit, String label) {
        this.suit = suit;
        this.label = label;
    }

    public static String from(Suit suit) {
        return Arrays.stream(values())
                .filter(suitLabel -> suitLabel.suit == suit)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("부적절한 슈트입니다."))
                .label;
    }
}
