package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.view.dto.CardDisplayName;
import java.util.Map;

public class CardMapper {
    
    private static final Map<Rank, String> RANK_NAMES = Map.of(
            Rank.ACE, "A",
            Rank.KING, "K",
            Rank.QUEEN, "Q",
            Rank.JACK, "J",
            Rank.TEN, "10"
    );
    
    private static final Map<Suit, String> SUIT_NAMES = Map.of(
            Suit.SPADES, "스페이드",
            Suit.DIAMONDS, "다이아몬드",
            Suit.HEARTS, "하트",
            Suit.CLUBS, "클로버"
    );
    
    public static CardDisplayName toDisplayName(Card card) {
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
