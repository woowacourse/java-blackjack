package domain.deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Card {

    private static List<Card> cards;

    private final Suit suit;
    private final Rank rank;

    private Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(final Suit suit, final Rank rank) {
        if (cards == null) {
            cards = new ArrayList<>();
            initializeCardStorage();
        }

        return cards.stream()
                .filter(card -> card.getRank() == rank && card.getSuit() == suit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]: 해당 카드가 없습니다."));
    }

    private static void initializeCardStorage() {
        for (Suit suit : Suit.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> cards.add(new Card(suit, rank)));
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
}
