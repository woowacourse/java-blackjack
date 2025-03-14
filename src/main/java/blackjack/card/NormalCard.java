package blackjack.card;


public class NormalCard extends Card {
    private NormalCard(CardSuit suit, CardRank rank) {
        super(suit, rank);
    }

    public static Card of(CardSuit suit, CardRank rank) {
        return new NormalCard(suit, rank);
    }

    public int getPoint() {
        return rank.getValue();
    }
}
