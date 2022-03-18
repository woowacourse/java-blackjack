package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class Fixtures {

    public static final Card ACE_DIAMOND = new Card(Denomination.DIAMOND, Suit.ACE);
    public static final Card JACK_DIAMOND = new Card(Denomination.DIAMOND, Suit.JACK);
    public static final Card KING_DIAMOND = new Card(Denomination.DIAMOND, Suit.KING);
    public static final Card TWO_DIAMOND = new Card(Denomination.DIAMOND, Suit.TWO);
    public static final Card SIX_DIAMOND = new Card(Denomination.DIAMOND, Suit.SIX);
    public static final Card SEVEN_DIAMOND = new Card(Denomination.DIAMOND, Suit.SEVEN);
    public static final Card ACE_HEART = new Card(Denomination.HEART, Suit.ACE);
}
