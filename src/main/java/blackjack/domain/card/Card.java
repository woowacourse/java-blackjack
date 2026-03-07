package domain.card;

public class Card {

    private final domain.card.CardRank rank;
    private final domain.card.CardSuit suit;

    public Card(domain.card.CardRank rank, domain.card.CardSuit suit) {
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
