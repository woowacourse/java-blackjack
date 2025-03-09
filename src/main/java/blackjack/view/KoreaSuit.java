package blackjack.view;

import blackjack.domain.card.Suit;

public enum KoreaSuit {
    SPADE("스페이드"),
    CLUB("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String displayName;

    KoreaSuit(String displayName) {
        this.displayName = displayName;
    }

    public static KoreaSuit fromSuit(Suit suit) {
        return KoreaSuit.valueOf(suit.name()); // Suit enum과 동일한 이름을 가정
    }

    public String getDisplayName() {
        return displayName;
    }
}


