package domain;

import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public String getRankName() {
        return rank.getName();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public int getScore() {
        return rank.getValue();
    }

    @Override
    public boolean equals(Object o) {
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
}
