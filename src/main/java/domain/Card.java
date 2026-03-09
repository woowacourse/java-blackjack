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
        return rank.getRankScore();
    }

    public String getDisplayName() {
        return rank.getRankName() + suit.getSuitName();
    }

    public boolean isAceCard() {
        return rank == Rank.ACE;
    }
}
