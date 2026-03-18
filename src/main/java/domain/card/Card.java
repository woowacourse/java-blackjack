package domain.card;

import domain.enums.Rank;
import domain.enums.Suit;

public record Card(Rank rank, Suit suit) {

    public int rankScore() {
        return rank.getScore();
    }
}
