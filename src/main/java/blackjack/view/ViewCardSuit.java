package blackjack.view;

import java.util.Arrays;

public enum ViewCardSuit {

    SPADE("SPADE", "스페이드"),
    DIAMOND("DIAMOND", "다이아몬드"),
    HEART("HEART", "하트"),
    CLUB("CLUB", "클로버"),
    ;

    final String cardSuitName;
    final String name;

    ViewCardSuit(final String name, final String cardSuitName) {
        this.cardSuitName = cardSuitName;
        this.name = name;
    }

    public static String getCardSuit(final String cardSuit) {
        return Arrays.stream(ViewCardSuit.values())
                .filter(it -> it.name.equals(cardSuit))
                .findAny()
                .get()
                .cardSuitName;
    }
}
