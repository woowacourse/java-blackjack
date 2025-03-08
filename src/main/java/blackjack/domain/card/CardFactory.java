package blackjack.domain.card;

public class CardFactory {

    private CardFactory() {
    }

    public static Card create(CardSuit suit, CardRank rank) {
        if (rank.isAce()) {
            return new AceCard(suit);
        }
        return new NormalCard(suit, rank);
    }
}
