package blackjack.card;

public class CardFactory {

    private CardFactory() {
    }

    protected static Card create(CardSuit suit, CardRank rank) {
        return resolveCard(suit, rank);
    }

    private static Card resolveCard(CardSuit suit, CardRank rank) {
        if (rank.isAce()) {
            return AceCard.from(suit);
        }
        return NormalCard.of(suit, rank);
    }
}
