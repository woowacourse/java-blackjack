package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.SPADE;

import blackjack.domain.card.Card;

public class CardFixtures {

    public static final Card ACE_SPACE = new Card(ACE, SPADE);
    public static final Card TWO_SPACE = new Card(TWO, SPADE);
    public static final Card THREE_SPACE = new Card(THREE, SPADE);
    public static final Card JACK_SPACE = new Card(JACK, SPADE);
    public static final Card KING_SPACE = new Card(KING, SPADE);
}
