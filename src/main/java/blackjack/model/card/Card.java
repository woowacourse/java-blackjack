package blackjack.model.card;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

    public static List<Card> createPool() {
        return Stream.of(Suit.values())
            .flatMap(Card::createEachSuitCards)
            .collect(Collectors.toList());
    }

    private static Stream<Card> createEachSuitCards(Suit suit) {
        return Stream.of(Rank.values())
            .map(rank -> new Card(rank, suit));
    }
}
