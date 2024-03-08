package blackjack.view;

import blackjack.domain.card.Suit;
import java.util.Arrays;

public enum SuitView {

    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String viewName;

    SuitView(final String viewName) {
        this.viewName = viewName;
    }

    public static String toSuitView(final Suit suit) {
        return Arrays.stream(SuitView.values())
                .filter(suitView -> suitView.name().equals(suit.name()))
                .findFirst()
                .orElseThrow()
                .viewName;
    }
}
