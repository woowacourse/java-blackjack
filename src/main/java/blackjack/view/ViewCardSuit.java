package blackjack.view;

import blackjack.domain.card.CardSuit;
import java.util.Arrays;

public enum ViewCardSuit {
    SPADE(CardSuit.SPADE, "스페이드"),
    DIAMOND(CardSuit.DIAMOND, "다이아몬드"),
    HEART(CardSuit.HEART, "하트"),
    CLUB(CardSuit.CLUB, "클로버"),
    ;

    final CardSuit cardSuit;
    final String cardSuitName;

    ViewCardSuit(CardSuit cardSuit, String cardSuitName) {
        this.cardSuit = cardSuit;
        this.cardSuitName = cardSuitName;
    }

    public static ViewCardSuit findCardSuit(CardSuit cardSuit) {
        return Arrays.stream(ViewCardSuit.values())
            .filter(suit -> suit.cardSuit.equals(cardSuit))
            .findFirst()
            .get();
    }

    public String getCardSuitName() {
        return cardSuitName;
    }
}
