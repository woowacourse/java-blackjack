package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;

public class Fixtures {

    public static final Card TWO_DIAMOND = Card.of(Suit.DIAMOND, Denomination.TWO);
    public static final Card THREE_DIAMOND = Card.of(Suit.DIAMOND, Denomination.THREE);
    public static final Card SIX_DIAMOND = Card.of(Suit.DIAMOND, Denomination.SIX);
    public static final Card SEVEN_DIAMOND = Card.of(Suit.DIAMOND, Denomination.SEVEN);
    public static final Card JACK_DIAMOND = Card.of(Suit.DIAMOND, Denomination.JACK);
    public static final Card KING_DIAMOND = Card.of(Suit.DIAMOND, Denomination.KING);
    public static final Card ACE_DIAMOND = Card.of(Suit.DIAMOND, Denomination.ACE);
    public static final Card ACE_HEART = Card.of(Suit.HEART, Denomination.ACE);
}
