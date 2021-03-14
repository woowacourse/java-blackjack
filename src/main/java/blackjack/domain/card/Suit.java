package blackjack.domain.card;

import java.util.Arrays;

public enum Suit {
    CLUB("클로버"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    static Suit of(final String value) {
        return Arrays.stream(Suit.values())
                .filter(suit -> suit.isMatch(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Suit입니다."));
    }

    private boolean isMatch(final String suit) {
        return this.suit.equals(suit);
    }

    public String suit() {
        return suit;
    }
}
