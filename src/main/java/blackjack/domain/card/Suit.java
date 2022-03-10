package blackjack.domain.card;

import java.util.Arrays;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String suitName;

    Suit(final String suitName) {
        this.suitName = suitName;
    }

    public static Suit of(final String suitNameInput) {
        return Arrays.stream(Suit.values())
                .filter(value -> value.suitName.equals(suitNameInput))
                .findAny()
                .orElseThrow();
    }

    public String getName() {
        return suitName;
    }
}
