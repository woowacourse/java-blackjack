package view.mapper;

import domain.card.Suit;

import java.util.Arrays;

public enum CardSuitMapper {
    HEART(Suit.HEART, "하트"),
    SPADE(Suit.SPADE, "스페이드"),
    CLOVER(Suit.CLOVER, "클로버"),
    DIAMOND(Suit.DIAMOND, "다이아몬드");

    private final Suit suit;
    private final String cardName;

    CardSuitMapper(Suit suit, String cardName) {
        this.suit = suit;
        this.cardName = cardName;
    }

    public static String getCardName(Suit suit) {
        return Arrays.stream(CardSuitMapper.values())
                .filter(it -> it.suit == suit)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 카드 타입이 없습니다."))
                .cardName;
    }
}
