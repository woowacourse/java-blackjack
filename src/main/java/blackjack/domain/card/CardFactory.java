package blackjack.domain.card;

public class CardFactory {

    private CardFactory() {
    }

    public static Card create(CardSuit suit, CardRank rank) {
        if (rank.isAce()) {
            return AceCard.from(suit);
        }
        return NormalCard.of(suit, rank);
    }
}
