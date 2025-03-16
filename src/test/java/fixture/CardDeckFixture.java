package fixture;

import domain.card.Card;
import domain.card.CardDeck;

public class CardDeckFixture {
    public static CardDeck createCardDeck(Card... cards) {
        CardsInitializerFixture cardsInitializerFixture = new CardsInitializerFixture(cards);
        return new CardDeck(cardsInitializerFixture);
    }
}
