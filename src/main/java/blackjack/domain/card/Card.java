package blackjack.domain.card;

import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class Card {
    private static final Map<String, Card> pool = blackjack.domain.card.Suit.stream()
            .flatMap(suit -> Rank.stream().map(rank -> new Card(rank, suit)))
            .collect(toMap(it -> toKey(it.rank, it.suit), Function.identity()));

    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(final Rank rank, final Suit suit) {
        return pool.get(toKey(rank, suit));
    }

    private static String toKey(final Rank rank, final Suit suit) {
        return rank.name() + suit.name();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public int score() {
        return rank.score();
    }

    public Rank rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }
}
