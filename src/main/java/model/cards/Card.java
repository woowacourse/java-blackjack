package model.cards;

import java.util.List;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(String rank, String suit) {
        this.rank = Rank.of(rank);
        this.suit = Suit.of(suit);
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public List<Integer> getRank() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return rank.getSymbol() + suit.getName();
    }
}
