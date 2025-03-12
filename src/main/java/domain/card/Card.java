package domain.card;

import java.util.List;

public class Card {
    private final Suit suit;
    private final Rank rank;
    private boolean isOpened;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.isOpened = false;
    }

    public void open() {
        this.isOpened = true;
    }

    public List<Integer> getScores() {
        return rank.getScores();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isOpened() {
        return isOpened;
    }
}
