package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Type;

public class CardFixture {

    public static final Card SPADE_ACE = Card.of(CardNumber.ACE, Type.SPADE);
    public static final Card SPADE_TWO = Card.of(CardNumber.TWO, Type.SPADE);
    public static final Card SPADE_THREE = Card.of(CardNumber.THREE, Type.SPADE);
    public static final Card SPADE_NINE = Card.of(CardNumber.NINE, Type.SPADE);
    public static final Card SPADE_TEN = Card.of(CardNumber.TEN, Type.SPADE);
}
