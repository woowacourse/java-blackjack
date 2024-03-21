package blackjack.view;

import blackjack.domain.card.CardSuit;

public enum CardSuitName {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private final String name;

    CardSuitName(String name) {
        this.name = name;
    }

    public static String convert(CardSuit cardSuit) {
        return valueOf(cardSuit.name()).name;
    }
}
