package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;

public class CardFixture {

    public static final Card SPADE_ACE = new Card(Symbol.SPADE, Denomination.ACE);
    public static final Card SPADE_TWO = new Card(Symbol.SPADE, Denomination.TWO);
    public static final Card SPADE_NINE = new Card(Symbol.SPADE, Denomination.NINE);
    public static final Card SPADE_JACK = new Card(Symbol.SPADE, Denomination.JACK);
    public static final Card DUMMY_CARD = new Card(Symbol.SPADE, Denomination.TWO);

}
