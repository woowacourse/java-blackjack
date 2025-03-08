package blackjack.domain.card;

public class CardFixture {

    public static Card createCard(CardSuit suit, CardRank rank) {
        return CardFactory.create(suit, rank);
    }
}
