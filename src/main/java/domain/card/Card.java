package domain.card;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Card {

    static final Map<String, Card> CARD_POOL;

    static {
        CARD_POOL = Suit.getValues().stream()
                .flatMap(suit -> Rank.getValues().stream()
                        .map(rank -> new Card(rank, suit))
                )
                .collect(Collectors.toMap(
                        card -> toKey(card.getRank(), card.getSuit()),
                        Function.identity()
                ));
    }

    private final Rank rank;
    private final Suit suit;

    private Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(final Rank rank, final Suit suit) {
        return CARD_POOL.get(toKey(rank, suit));
    }

    private static String toKey(final Rank rank, final Suit suit) {
        return rank.name() + "-" + suit.name();
    }

    public boolean isAceCard() {
        return this.rank.isAce();
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Card) obj;
        return Objects.equals(this.rank, that.rank) &&
                Objects.equals(this.suit, that.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

}
