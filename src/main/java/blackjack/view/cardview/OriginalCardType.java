package blackjack.view.cardview;

import blackjack.domain.card.CardType;

import java.util.Arrays;

public enum OriginalCardType {

    HEART(CardType.HEART, "하트"),
    DIAMOND(CardType.DIAMOND, "다이아몬드"),
    SPADE(CardType.SPADE, "스페이드"),
    CLOVER(CardType.CLOVER, "클로버");

    private final CardType cardType;
    private final String name;

    OriginalCardType(CardType cardType, String name) {
        this.cardType = cardType;
        this.name = name;
    }

    public static String getOriginalName(CardType cardType) {
        return Arrays.stream(values())
                .filter(c -> c.getCardType() == cardType)
                .map(OriginalCardType::getName)
                .findAny()
                .orElseThrow(NullPointerException::new);
    }

    private CardType getCardType() {
        return cardType;
    }

    private String getName() {
        return name;
    }
}
