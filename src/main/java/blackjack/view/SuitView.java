package blackjack.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

import blackjack.domain.card.Suit;

public enum SuitView {

    DIAMOND(Suit.DIAMOND, "다이아몬드"),
    CLOVER(Suit.CLOVER, "클로버"),
    SPADE(Suit.SPADE, "스페이드"),
    HEART(Suit.HEART, "하트");

    public static final String NO_SUCH_SUIT = "일치하는 suit가 없습니다.";

    private final Suit suit;
    private final String name;

    SuitView(Suit suit, String name) {
        this.suit = suit;
        this.name = name;
    }

    public static String getName(Suit suit) {
        return Arrays.stream(values())
            .filter(s -> suit == s.getSuit())
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(NO_SUCH_SUIT))
            .name;
    }

    public Suit getSuit() {
        return suit;
    }
}
