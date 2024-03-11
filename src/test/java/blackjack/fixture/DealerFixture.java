package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;

import static blackjack.fixture.CardFixture.전체_카드;
import static blackjack.fixture.CardFixture.카드들;

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
