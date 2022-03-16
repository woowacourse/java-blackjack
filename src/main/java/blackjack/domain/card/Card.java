package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private final CardRank rank;
    private final CardSymbol symbol;

    private Card(CardRank rank, CardSymbol symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public static Card of(CardRank rank, CardSymbol symbol) {
        return CardCache.getCache(rank, symbol);
    }

    public boolean isAce() {
        return rank == CardRank.ACE;
    }

    public Score getRankValue() {
        return rank.getValue();
    }

    public String getName() {
        return rank.getDisplayName() + symbol.getDisplayName();
    }

    @Override
    public String toString() {
        return "Card{" + getName() + "}";
    }

    private static class CardCache {
        private static final String NOT_EXISTING_CARD_EXCEPTION_MESSAGE = "존재하지 않는 카드입니다.";

        private static final Set<Card> cache;

        static {
            cache = Stream.of(CardSymbol.values())
                    .flatMap(symbol -> Stream.of(CardRank.values())
                            .map(rank -> new Card(rank, symbol)))
                    .collect(Collectors.toSet());
        }

        static Card getCache(CardRank rank, CardSymbol symbol) {
            return cache.stream()
                    .filter(card -> isSameCard(card, rank, symbol))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTING_CARD_EXCEPTION_MESSAGE));
        }

        private static boolean isSameCard(Card card, CardRank rank, CardSymbol symbol) {
            boolean isSameRank = card.rank == rank;
            boolean isSameSymbol = card.symbol == symbol;

            return isSameRank && isSameSymbol;
        }
    }
}
