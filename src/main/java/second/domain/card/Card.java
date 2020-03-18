package second.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    private static final String CARD_NO_EXSIST_MESSAGE = "적절한 number 또는 symbol의 카드가 존재하지 않습니다.";

    private final Rank rank;
    private final Suit suit;

    private Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(final Rank rank, final Suit suit) {
        return CardCache.cards
                .stream()
                .filter(card -> card.is(rank, suit))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(CARD_NO_EXSIST_MESSAGE));
    }

    private boolean is(final Rank rank,final Suit suit) {
        return this.rank.is(rank) && this.suit.is(suit);
    }

    boolean isAce() {
        return rank.is(Rank.ACE);
    }

    public int extractScore() {
        return rank.getValue();
    }

    public static List<Card> makeAllCards() {
        return Collections.unmodifiableList(CardCache.cards);
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    private static class CardCache {
        private static final List<Card> cards = new ArrayList<>();

        static {
            for (Rank rank : Rank.values()) {
                cards.addAll(generateAllSymbolCard(rank));
            }
        }

        private static List<Card> generateAllSymbolCard(final Rank rank) {
            List<Card> allSymbolCards = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                allSymbolCards.add(new Card(rank, suit));
            }
            return allSymbolCards;
        }
    }
}
