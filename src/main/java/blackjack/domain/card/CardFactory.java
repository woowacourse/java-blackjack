package blackjack.domain.card;

import java.util.EnumMap;
import java.util.Map;

public class CardFactory {

    private static final Map<CardSuit, Map<CardRank, Card>> CACHE = new EnumMap<>(CardSuit.class);

    static {
        for (CardSuit suit : CardSuit.values()) {
            Map<CardRank, Card> rankMap = new EnumMap<>(CardRank.class);
            for (CardRank rank : CardRank.values()) {
                rankMap.put(rank, resolveCard(suit, rank));
            }
            CACHE.put(suit, rankMap);
        }
    }

    private CardFactory() {
    }

    protected static Card create(CardSuit suit, CardRank rank) {
        return CACHE.get(suit).get(rank);
    }

    private static Card resolveCard(CardSuit suit, CardRank rank) {
        if (rank.isAce()) {
            return AceCard.from(suit);
        }
        return NormalCard.of(suit, rank);
    }
}
