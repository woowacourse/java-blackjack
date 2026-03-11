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

    public String getCardInfo() {
        return rank.getName() + suit.getSuit();
    }

    public boolean isAce() {
        return this.rank == CardRank.ACE;
    }
}
