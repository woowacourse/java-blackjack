package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.Map;

public record CardDto(
    String cardName
) {
    private static final Map<Suit, String> SUIT_KOREANS = Map.of(
        Suit.DIAMOND, "다이아몬드",
        Suit.CLOVER, "클로버",
        Suit.SPADE, "스페이드",
        Suit.HEART, "하트"
    );

    private static final Map<Rank, String> RANK_SYMBOLS = Map.ofEntries(
        Map.entry(Rank.ACE, "A"),
        Map.entry(Rank.TWO, "2"),
        Map.entry(Rank.THREE, "3"),
        Map.entry(Rank.FOUR, "4"),
        Map.entry(Rank.FIVE, "5"),
        Map.entry(Rank.SIX, "6"),
        Map.entry(Rank.SEVEN, "7"),
        Map.entry(Rank.EIGHT, "8"),
        Map.entry(Rank.NINE, "9"),
        Map.entry(Rank.TEN, "10"),
        Map.entry(Rank.JACK, "J"),
        Map.entry(Rank.QUEEN, "Q"),
        Map.entry(Rank.KING, "K")
    );

    public static CardDto from(Card card) {
        return new CardDto(RANK_SYMBOLS.get(card.rank()) + SUIT_KOREANS.get(card.suit()));
    }
}
