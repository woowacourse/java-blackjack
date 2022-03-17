package blackjack.model.card;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int hardRank() {
        return rank.hard();
    }

    public int softRank() {
        return rank.soft();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public Rank rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return rank.hard() + "-" + suit;
    }

    static List<Card> createPool() {
        List<Card> pool = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            pool.addAll(createCardsEach(suit));
        }
        return pool;
    }

    private static Collection<Card> createCardsEach(Suit suit) {
        return Stream.of(Rank.values())
            .map(rank -> new Card(rank, suit))
            .collect(toUnmodifiableList());
    }
}
