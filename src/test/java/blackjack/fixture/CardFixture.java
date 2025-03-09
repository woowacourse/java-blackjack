package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;

public class CardFixture {

    public static Card make(CardValue cardValue) {
        return new Card(CardShape.HEART, cardValue);
    }
}
