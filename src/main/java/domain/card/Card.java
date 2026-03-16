package domain.card;

import domain.enums.Rank;
import domain.enums.Suit;

public record Card(Rank rank, Suit suit) {

    public String rankString() {
        return rank.getRank();
    }

    public String suitString() {
        return suit.getSuit();
    }

    public int rankScore() {
        return rank.getScore();
    }
}
