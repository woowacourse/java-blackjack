package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import rentcar.Car;

public class Fixtures {
    public static final Card SPADE_ACE = Card.valueOf(Suit.SPADE, Denomination.ACE);
    public static final Card SPADE_TWO = Card.valueOf(Suit.SPADE, Denomination.TWO);
    public static final Card SPADE_THREE = Card.valueOf(Suit.SPADE, Denomination.THREE);
    public static final Card SPADE_EIGHT = Card.valueOf(Suit.SPADE, Denomination.EIGHT);
    public static final Card SPADE_NINE = Card.valueOf(Suit.SPADE, Denomination.NINE);
    public static final Card SPADE_TEN = Card.valueOf(Suit.SPADE, Denomination.TEN);

}
