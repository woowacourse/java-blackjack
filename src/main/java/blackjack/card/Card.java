package blackjack.card;

public abstract class Card {

    protected final CardSuit suit;
    protected final CardRank rank;
    protected CardSide side;

    protected Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
        this.side = CardSide.getDefault();
    }

    public static Card of(CardSuit suit, CardRank rank) {
        return CardFactory.create(suit, rank);
    }

    public void reverse() {
        side = side.reverse();
    }

    public boolean isHidden() {
        return side.isHidden();
    }

    public boolean isNotHidden() {
        return !isHidden();
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
