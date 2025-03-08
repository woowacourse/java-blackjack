package blackjack.domain.card;

public abstract class Card {

    protected final CardSuit suit;
    protected final CardRank rank;

    protected Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isAce() {
        return this.rank.equals(CardRank.ACE);
    }

    public boolean isNotAce() {
        return !isAce();
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }
}
