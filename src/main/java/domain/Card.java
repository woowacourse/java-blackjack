package domain;

import constant.Rank;
import constant.Suit;

public record Card(Rank rank, Suit suit) {

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

}
