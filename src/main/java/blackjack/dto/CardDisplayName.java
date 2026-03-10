package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import java.util.Map;

public record CardDisplayName(String displayName) {
    
    private static final Map<Rank, String> RANK_NAMES = Map.of(
            Rank.ACE, "A",
            Rank.KING, "K",
            Rank.QUEEN, "Q",
            Rank.JACK, "J",
            Rank.TEN, "10"
    );
    
    private static final Map<Suit, String> SUIT_NAMES = Map.of(
            Suit.SPADE, "스페이드",
            Suit.DIAMOND, "다이아몬드",
            Suit.HEART, "하트",
            Suit.CLOVER, "클로버"
    );
    
    public static List<CardDisplayName> listOf(List<Card> cards) {
        return cards.stream()
                .map(CardDisplayName::from)
                .toList();
    }
    
    public static CardDisplayName from(Card card) {
        String rankName = translateRank(card.getRank());
        String suitName = SUIT_NAMES.get(card.getSuit());
        return new CardDisplayName(rankName + suitName);
    }
    
    private static String translateRank(Rank rank) {
        if (RANK_NAMES.containsKey(rank)) {
            return RANK_NAMES.get(rank);
        }
        return String.valueOf(rank.getScore());
    }
}
