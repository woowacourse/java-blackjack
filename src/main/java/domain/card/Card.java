package domain.card;

import domain.Rank;
import domain.Suit;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getOneIfAce() {
        if (rank.isAce()) {
            return 1;
        }
        return 0;
    }

    public int getRankValueIfNotAce() {
        if (!rank.isAce()) {
            return rank.getScoreValue();
        }
        return 0;
    }

    @Override
    public String toString() {
        return rank.getDisplayValue() + suit.getValue();
    }
}
