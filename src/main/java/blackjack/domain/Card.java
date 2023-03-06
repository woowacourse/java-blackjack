package blackjack.domain;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Objects;

public class Card {
    private static final Map<Suit, Map<Rank, Card>> CACHE;

    static {
        CACHE = Rank.stream()
                .flatMap(rank -> Suit.stream()
                        .map(suit -> new Card(suit, rank))
                ).collect(groupingBy(card -> card.suit,
                        toMap(card -> card.rank, card -> card)));
    }

    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(Suit suit, Rank rank) {
        return CACHE.get(suit).get(rank);
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public int getScore() {
        return rank.getValue();
    }

    public String getSuit() {
        return suit.getName();
    }

    public String getRank() {
        return rank.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
