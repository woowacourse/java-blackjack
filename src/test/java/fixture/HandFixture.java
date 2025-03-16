package fixture;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Hand;

public class HandFixture {
    public static Hand createHand(Card card1, Card card2) {
        CardDeck cardDeck = CardDeckFixture.createCardDeck(card1, card2);
        return new Hand(cardDeck);
    }
}
