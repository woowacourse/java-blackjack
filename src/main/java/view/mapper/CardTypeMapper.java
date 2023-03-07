package view.mapper;

import domain.card.CardType;

import java.util.Arrays;

public enum CardTypeMapper {

    HEART(CardType.HEART, "하트"),
    SPADE(CardType.SPADE, "스페이드"),
    CLOVER(CardType.CLOVER, "클로버"),
    DIAMOND(CardType.DIAMOND, "다이아몬드");

    private static final String NO_SUCH_CARD_TYPE_MESSAGE = "[ERROR] 카드 타입이 정의되어 있지 않습니다.";

    private final CardType cardType;
    private final String message;

    CardTypeMapper(final CardType cardType, final String message) {
        this.cardType = cardType;
        this.message = message;
    }

    public static String ofCardType(final CardType cardType) {
        return Arrays.stream(CardTypeMapper.values())
                .filter(it -> it.cardType == cardType)
                .map(cardTypeMapper -> cardTypeMapper.message)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_CARD_TYPE_MESSAGE));
    }
}
