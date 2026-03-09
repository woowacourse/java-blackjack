package domain;

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
        return rank.getScore();
    }

    public String getDisplayName() {
        return rank.getName() + suit.getName();
    }

    public boolean isAceCard() {
        return rank == Rank.ACE;
    }
}
