package blackjack.domain;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toConcurrentMap;

public class Card {

    private static final Map<Integer, Card> CACHE;

    static {
        CACHE = Rank.stream()
                .flatMap(rank -> Suit.stream()
                        .map(suit -> new Card(suit, rank))
                ).collect(toConcurrentMap(Card::hashCode, Function.identity()));
    }

    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(Suit suit, Rank rank) {
        return CACHE.get(Objects.hash(suit, rank));
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
