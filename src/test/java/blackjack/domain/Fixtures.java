package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;

public class Fixtures {

    public static final Card TWO_DIAMOND = new Card(Suit.DIAMOND, Denomination.TWO);
    public static final Card THREE_DIAMOND = new Card(Suit.DIAMOND, Denomination.THREE);
    public static final Card SIX_DIAMOND = new Card(Suit.DIAMOND, Denomination.SIX);
    public static final Card SEVEN_DIAMOND = new Card(Suit.DIAMOND, Denomination.SEVEN);
    public static final Card JACK_DIAMOND = new Card(Suit.DIAMOND, Denomination.JACK);
    public static final Card KING_DIAMOND = new Card(Suit.DIAMOND, Denomination.KING);
    public static final Card ACE_DIAMOND = new Card(Suit.DIAMOND, Denomination.ACE);
    public static final Card ACE_HEART = new Card(Suit.HEART, Denomination.ACE);
}
