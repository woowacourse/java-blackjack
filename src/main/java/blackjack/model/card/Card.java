package blackjack.model.card;

public class Card {

    private final Rank rank;
    private final Suit suit;

    private Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(final Rank rank, final Suit suit) {
        return new Card(rank, suit);
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public int getDefaultScore() {
        return rank.getDefaultScore();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}
