package blackjack.view;

import blackjack.domain.card.Suit;

public enum SuitDescription {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String description;

    SuitDescription(final String description) {
        this.description = description;
    }

    public static SuitDescription from(Suit suit) {
        return SuitDescription.valueOf(suit.name());
    }

    public String getDescription() {
        return description;
    }
}
