package fixture;

import blackjack.card.Card;
import blackjack.card.CardDeck;

public class CardDeckFixture {
    public static CardDeck createCardDeck(Card... cards) {
        CardsInitializerFixture cardsInitializerFixture = new CardsInitializerFixture(cards);
        return new CardDeck(cardsInitializerFixture);
    }
}
