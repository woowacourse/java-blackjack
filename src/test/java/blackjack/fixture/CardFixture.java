package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

public class CardFixture {

    public static Card cardOf(CardNumber cardNumber) {
        return new Card(CardType.CLOVER, cardNumber);
    }
}
