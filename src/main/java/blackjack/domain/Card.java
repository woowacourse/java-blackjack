package blackjack.domain;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public String getDisplayName() {
        return String.format("%s%s", rank.getRankName(), suit.getSuitDisplayName());
    }
}
