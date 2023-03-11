package blackjack.domain.card;

import blackjack.domain.card.dto.CardResponse;

public class CardResponseFixture {

    public static final CardResponse SPADE_ACE_RESPONSE = CardResponse.from(
            new Card(Shape.SPADE, Symbol.ACE));
    public static final CardResponse CLOVER_ACE_RESPONSE = CardResponse.from(
            new Card(Shape.CLOVER, Symbol.ACE));
    static final CardResponse HEART_EIGHT_RESPONSE = CardResponse.from(
            new Card(Shape.HEART, Symbol.EIGHT));
}
