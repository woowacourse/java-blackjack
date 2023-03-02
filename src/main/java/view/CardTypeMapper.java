package view;

import domain.CardType;

import java.util.Arrays;

public enum CardTypeMapper {
    HEART(CardType.HEART, "하트"),
    SPADE(CardType.SPADE, "스페이드"),
    CLOVER(CardType.CLOVER, "클로버"),
    DIAMOND(CardType.DIAMOND, "다이아몬드");

    private final CardType cardType;
    private final String cardName;

    CardTypeMapper(CardType cardType, String cardName) {
        this.cardType = cardType;
        this.cardName = cardName;
    }

    public static String getCardName(CardType cardType) {
        return Arrays.stream(CardTypeMapper.values())
                .filter(it -> it.cardType == cardType)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 카드 타입이 없습니다."))
                .cardName;
    }
}
