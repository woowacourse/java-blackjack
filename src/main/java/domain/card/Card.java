package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {

    private static final List<Card> cache = new ArrayList<>();

    private final Rank rank;
    private final Suit suit;

    static {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cache.add(new Card(rank, suit));
            }
        }
    }

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card getCard(Rank rank, Suit suit) {
        return cache.stream()
                .filter(card -> card.rank.equals(rank))
                .filter(card -> card.suit.equals(suit))
                .findFirst()
                .orElse(new Card(rank, suit));
    }

    public static List<Card> getShuffledCards() {
        ArrayList<Card> cards = new ArrayList<>(cache);
        Collections.shuffle(cards);
        return cards;
    }

    public int getPoint() {
        return rank.getPoint();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public String combineRankAndSuit() {
        return rank.getRank() + suit.getSuit();
    }
}
