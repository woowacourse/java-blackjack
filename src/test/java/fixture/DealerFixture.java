package fixture;

import static fixture.CardFixture.전체_카드;
import static fixture.CardFixture.카드들;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;

public class DealerFixture {
    public static Dealer 딜러(Card... cards) {
        CardDeck cardDeck = new CardDeck(카드들(cards));
        return new Dealer(cardDeck, cardList -> {
        });
    }

    public static Dealer 딜러() {
        CardDeck cardDeck = new CardDeck(전체_카드());
        return new Dealer(cardDeck, cardList -> {
        });
    }
}
