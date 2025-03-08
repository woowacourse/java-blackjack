package blackjack.domain.card;


public class NormalCard extends Card {
    public NormalCard(CardSuit suit, CardRank rank) {
        super(suit, rank);
    }

    public int getPoint() {
        return rank.getValue();
    }
}
