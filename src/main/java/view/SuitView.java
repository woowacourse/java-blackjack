package view;

import domain.card.Suit;
import java.util.Arrays;

public enum SuitView {
    CLUB(Suit.CLUB, "클로버"),
    HEART(Suit.HEART, "하트"),
    DIAMOND(Suit.DIAMOND, "다이아몬드"),
    SPADE(Suit.SPADE, "스페이드");

    private final Suit suit;
    private final String displayName;

    SuitView(Suit suit, String displayName) {
        this.suit = suit;
        this.displayName = displayName;
    }

    public static String from(Suit suit) {
        return Arrays.stream(values())
                .filter(view -> view.suit == suit)
                .findFirst()
                .map(view -> view.displayName)
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 수트입니다."));
    }
}
