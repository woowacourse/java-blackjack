package blackjack.view;

import blackjack.domain.card.Suit;
import java.util.Arrays;

public enum SuitView {

    SPADES("스페이드"),
    DIAMONDS("다이아몬드"),
    HEARTS("하트"),
    CLUBS("클로버"),
    ;

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
