package blackjack.util;

import java.util.stream.Stream;

public enum CardSuit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버"),
    ;

    final String cardSuitName;

    CardSuit(String cardSuitName) {
        this.cardSuitName = cardSuitName;
    }

    public static CardSuit getCardSuit(CardSuit cardSuit) {
        return Stream.of(CardSuit.values())
            .filter(suit -> suit.equals(cardSuit))
            .findFirst()
            .get();
    }

    public String getCardSuitName() {
        return cardSuitName;
    }
}
