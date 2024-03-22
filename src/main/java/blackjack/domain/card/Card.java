package blackjack.domain.card;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Card {

    // 52장의 Card를 미리 Pool에 생성
    private static final Map<String, Card> CARD_POOL = Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Rank.values())
                    .map(rank -> new Card(suit, rank)))
            .collect(Collectors.toMap(card ->
                    toKey(card.suit, card.rank), Function.identity()));

    private final Suit suit;
    private final Rank rank;

    private Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(final Suit suit, final Rank rank) {
        return CARD_POOL.get(toKey(suit, rank));
    }

    private static String toKey(final Suit suit, final Rank rank) {
        return suit.getName() + rank.getName();
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public int getScore() {
        return rank.getScore();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public String getRankName() {
        return rank.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
