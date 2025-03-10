package blackjack.view;

import blackjack.domain.card.Suit;

public enum SuitLabel {
    SPADE("스페이드"),
    CLUB("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String displayName;

    SuitLabel(String displayName) {
        this.displayName = displayName;
    }

    public static SuitLabel fromSuit(Suit suit) {
        return SuitLabel.valueOf(suit.name()); // Suit enum과 동일한 이름을 가정
    }

    public String getDisplayName() {
        return displayName;
    }
}


