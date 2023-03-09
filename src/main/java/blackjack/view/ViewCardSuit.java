package blackjack.view;

import blackjack.domain.card.CardSuit;

import java.util.Arrays;

public enum ViewCardSuit {

    SPADE(CardSuit.SPADE, "스페이드"),
    DIAMOND(CardSuit.DIAMOND, "다이아몬드"),
    HEART(CardSuit.HEART, "하트"),
    CLUB(CardSuit.CLUB, "클로버"),
    ;

    final String cardSuitName;
    final CardSuit cardSuit;

    ViewCardSuit(final CardSuit cardSuit, final String cardSuitName) {
        this.cardSuitName = cardSuitName;
        this.cardSuit = cardSuit;
    }

    public static String getCardSuit(final CardSuit cardSuit) {
        return Arrays.stream(ViewCardSuit.values())
                .filter(it -> it.cardSuit == cardSuit)
                .findAny()
                .get()
                .getCardSuitName();
    }

    public String getCardSuitName() {
        return cardSuitName;
    }
}
