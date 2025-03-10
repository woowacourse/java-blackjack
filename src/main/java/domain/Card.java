package domain;

import domain.constant.TrumpSuit;
import domain.constant.TrumpRank;

public class Card {

    private final TrumpRank rank;
    private final TrumpSuit suit;

    public Card(TrumpRank rank, TrumpSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAce() {
        return rank == TrumpRank.ACE;
    }

    public int getRankScore() {
        return rank.getValue();
    }

    public TrumpRank getRank() {
        return rank;
    }

    public TrumpSuit getSuit() {
        return suit;
    }
}
