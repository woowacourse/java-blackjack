package fixture;

import static fixture.CardFixture.카드들;

import domain.Card;
import domain.CardDeck;
import domain.Dealer;

public class DealerFixture {
    public static Dealer 딜러(Card... cards) {
        CardDeck cardDeck = new CardDeck(카드들(cards));
        return new Dealer(cardDeck, cardList -> {
        });
    }
}
