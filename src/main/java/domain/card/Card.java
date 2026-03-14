package domain.card;

import constant.Rank;
import constant.Suit;
import java.util.Objects;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(constant.Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
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

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }

    public String getName() {
        return rank.getRank() + suit.getSuit();
    }
}
