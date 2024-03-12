package blackjack.view.mapper;

import blackjack.domain.card.CardRank;
import java.util.Arrays;

public enum CardRankMapper {

    ACE(CardRank.ACE, "A"),
    TWO(CardRank.TWO, "2"),
    THREE(CardRank.THREE, "3"),
    FOUR(CardRank.FOUR, "4"),
    FIVE(CardRank.FIVE, "5"),
    SIX(CardRank.SIX, "6"),
    SEVEN(CardRank.SEVEN, "7"),
    EIGHT(CardRank.EIGHT, "8"),
    NINE(CardRank.NINE, "9"),
    TEN(CardRank.TEN, "10"),
    JACK(CardRank.JACK, "J"),
    QUEEN(CardRank.QUEEN, "Q"),
    KING(CardRank.KING, "K");

    private final CardRank cardRank;
    private final String symbol;

    CardRankMapper(CardRank cardRank, String symbol) {
        this.cardRank = cardRank;
        this.symbol = symbol;
    }

    public static String toSymbol(CardRank cardRank) {
        return Arrays.stream(values())
                .filter(it -> it.cardRank == cardRank)
                .findFirst()
                .map(it -> it.symbol)
                .orElseThrow(IllegalArgumentException::new);
    }
}
