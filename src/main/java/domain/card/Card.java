package domain.card;

import domain.enums.Rank;
import domain.enums.Suit;

public record Card(Rank rank, Suit suit) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    public int getRankScore() {
        return rank.getScore();
    }
}
