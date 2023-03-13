package blackjack.view;

import blackjack.domain.card.Suit;

import java.util.Arrays;

public enum SuitMapper {

    SPADE(Suit.SPADE, "♠️"),
    DIAMOND(Suit.DIAMOND, "♦️"),
    HEART(Suit.HEART, "♥️"),
    CLOVER(Suit.CLOVER, "♣️");

    private final Suit suit;
    private final String value;

    SuitMapper(Suit suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public static String map(Suit suit) {
        return Arrays.stream(values())
                .filter(suitMapper -> suitMapper.suit.equals(suit))
                .map(SuitMapper::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 무늬가 없습니다."));
    }

    public String getValue() {
        return value;
    }
}
