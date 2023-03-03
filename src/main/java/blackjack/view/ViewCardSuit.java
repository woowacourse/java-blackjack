package blackjack.view;

import blackjack.domain.CardSuit;
import java.util.stream.Stream;

public enum ViewCardSuit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버"),
    ;

    final String cardSuitName;

    ViewCardSuit(String cardSuitName) {
        this.cardSuitName = cardSuitName;
    }

    public static ViewCardSuit getCardSuit(CardSuit cardSuit) {
        return Stream.of(ViewCardSuit.values())
            .filter(suit -> suit.equals(cardSuit))
            .findFirst()
            .get();
    }

    public String getCardSuitName() {
        return cardSuitName;
    }
}
