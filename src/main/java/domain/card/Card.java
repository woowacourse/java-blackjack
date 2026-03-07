package domain.card;

public class Card {

    private final CardRank rank;
    private final CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getScore() {
        return rank.getScore();
    }

    public String getCardInfo() {
        return rank.getRank() + suit.getSuit();
    }

    public boolean isAce() {
        return rank.isAce();
    }
}
