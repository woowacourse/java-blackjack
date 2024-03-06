package blackjack.view;

import blackjack.domain.card.Suit;
import java.util.Arrays;

enum SuitTranslator {
    HEART(Suit.HEART, "하트"),
    SPADE(Suit.SPADE, "스페이드"),
    CLOVER(Suit.CLOVER, "클로버"),
    DIAMOND(Suit.DIAMOND, "다이아몬드");

    private final Suit suit;
    private final String viewName;

    SuitTranslator(final Suit suit, final String viewName) {
        this.suit = suit;
        this.viewName = viewName;
    }

    public static String translate(final Suit suit) {
        return Arrays.stream(SuitTranslator.values())
                .filter(suitTranslator -> suitTranslator.suit == suit)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("매칭되는 슈트가 없습니다."))
                .viewName;
    }

    public Suit getSuit() {
        return suit;
    }
}
