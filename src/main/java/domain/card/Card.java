package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private static final String CARD_NO_EXSIST_MESSAGE = "적절한 number 또는 symbol의 카드가 존재하지 않습니다.";
    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(Rank rank, Suit suit) {
        return CardCache.cards
                .stream()
                .filter(card -> card.rank == rank)
                .filter(card -> card.suit == suit)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(CARD_NO_EXSIST_MESSAGE));
    }

    public static List<Card> getAllCards() {
        return CardCache.cards;
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public int extractScore() {
        return rank.getScore();
    }

    private static class CardCache {
        private static final List<Card> cards = new ArrayList<>();

        static {
            for (Rank rank : Rank.values()) {
                cards.addAll(generateAllSymbolCard(rank));
            }
        }

        private static List<Card> generateAllSymbolCard(Rank rank) {
            List<Card> allSymbolCards = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                allSymbolCards.add(new Card(rank, suit));
            }
            return allSymbolCards;
        }
    }
}
