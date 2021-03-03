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

    static Suit of(String suit){
        return Arrays.stream(Suit.values())
                .filter(s -> s.suit.equals(suit))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 Suit입니다."));
    }

    public String suit() {
        return suit;
    }
}
