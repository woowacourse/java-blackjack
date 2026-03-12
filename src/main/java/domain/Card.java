package domain;

import java.util.Objects;
import vo.Rank;
import vo.Suit;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getCardScore() {
        return rank.getRankScore();
    }

    public String getDisplayName() {
        return rank.getRankName() + suit.getSuitName();
    }

    public boolean isAceCard() {
        return rank == Rank.ACE;
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
