package domain.fixture;

import domain.card.CardDeck;
import domain.card.CardValue;

public class CardDeckFixture {

    public static CardDeck cardDeck(final CardValue... cardValues) {
        return CardDeck.shuffledFullCardDeck();
    }
}
