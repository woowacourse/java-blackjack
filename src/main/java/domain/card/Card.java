package domain.card;

public class Card {
    private final CardRank rank;
    private final CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRankScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return this.rank == CardRank.ACE;
    }

    public CardRank getCardRank() {
        return this.rank;
    }

    public CardSuit getCardSuit() {
        return this.suit;
    }
}
