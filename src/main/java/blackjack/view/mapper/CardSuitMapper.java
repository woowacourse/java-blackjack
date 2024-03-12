package blackjack.view.mapper;

import blackjack.domain.card.CardSuit;
import java.util.Arrays;

public enum CardSuitMapper {

    HEART(CardSuit.HEART, "하트"),
    DIAMOND(CardSuit.DIAMOND, "다이아몬드"),
    CLUB(CardSuit.CLUB, "클로버"),
    SPADE(CardSuit.SPADE, "스페이드");

    private final CardSuit cardSuit;
    private final String symbol;

    CardSuitMapper(CardSuit cardSuit, String symbol) {
        this.cardSuit = cardSuit;
        this.symbol = symbol;
    }

    public static String toSymbol(CardSuit cardSuit) {
        return Arrays.stream(values())
                .filter(it -> it.cardSuit == cardSuit)
                .findFirst()
                .map(it -> it.symbol)
                .orElseThrow(IllegalArgumentException::new);
    }
}
