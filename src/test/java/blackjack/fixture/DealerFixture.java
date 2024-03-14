package blackjack.fixture;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;

public class DealerFixture {

    public static Dealer 딜러() {
        final CardDeck cardDeck = new CardDeck();
        return new Dealer(cardDeck);
    }
}
