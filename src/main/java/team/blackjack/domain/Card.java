package team.blackjack.domain;

import java.util.List;
import java.util.Objects;

public final class Card {
    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(Suit suit, Rank rank) {
        return new Card(suit, rank);
    }

    public String getCardName() {
        return this.rank.getName() + this.suit.getKoreanName();
    }

    public boolean isAce() {
        return this.rank.isAce();
    }

    public List<Integer> getScore() {
        return this.rank.getScore();
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
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
