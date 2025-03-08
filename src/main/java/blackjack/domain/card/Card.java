package blackjack.domain.card;

public abstract class Card {

    protected final CardSuit suit;
    protected final CardRank rank;

    protected Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // TODO OCP 위반 해결
    public static Card of(CardSuit suit, CardRank rank) {
        if (rank == CardRank.ACE) {
            return new AceCard(suit);
        }
        return new NormalCard(suit, rank);
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
