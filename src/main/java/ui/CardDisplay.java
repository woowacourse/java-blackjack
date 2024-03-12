package ui;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Emblem;
import java.util.Arrays;

public enum CardDisplay {
    ACE(Denomination.ACE, "A"),
    TWO(Denomination.TWO, "2"),
    THREE(Denomination.THREE, "3"),
    FOUR(Denomination.FOUR, "4"),
    FIVE(Denomination.FIVE, "5"),
    SIX(Denomination.SIX, "6"),
    SEVEN(Denomination.SEVEN, "7"),
    EIGHT(Denomination.EIGHT, "8"),
    NINE(Denomination.NINE, "9"),
    TEN(Denomination.TEN, "10"),
    JACK(Denomination.JACK, "J"),
    QUEEN(Denomination.QUEEN, "Q"),
    KING(Denomination.KING, "K");

    private final Denomination denomination;
    private final String expression;

    CardDisplay(Denomination denomination, String expression) {
        this.denomination = denomination;
        this.expression = expression;
    }

    static String generateCardMessage(Card card) {
        String denominationExpression = translateToExpression(card.getDenomination());
        String emblemName = EmblemDisplay.translateToName(card.getEmblem());
        return denominationExpression + emblemName;
    }

    private static String translateToExpression(Denomination denomination) {
        return Arrays.stream(values())
                .filter(cardDisplay -> cardDisplay.denomination == denomination)
                .map(cardDisplay -> cardDisplay.expression)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("display가 존재하지 않습니다."));
    }

    private enum EmblemDisplay {
        CLOVER(Emblem.CLOVER, "클로버"),
        SPADE(Emblem.SPADE, "스페이드"),
        HEART(Emblem.HEART, "하트"),
        DIAMOND(Emblem.DIAMOND, "다이아몬드");

        private final Emblem emblem;
        private final String name;

        EmblemDisplay(Emblem emblem, String name) {
            this.emblem = emblem;
            this.name = name;
        }

        static String translateToName(Emblem emblem) {
            return Arrays.stream(values())
                    .filter(emblemDisplay -> emblemDisplay.emblem == emblem)
                    .map(emblemDisplay -> emblemDisplay.name)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("display가 존재하지 않습니다."));
        }
    }
}
