package blackjack.view;

import blackjack.domain.card.Suit;

public enum SuitName {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private final String name;

    SuitName(String name) {
        this.name = name;
    }

    public static String convert(Suit suit) {
        return valueOf(suit.name()).name;
    }
}
