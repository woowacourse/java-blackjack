package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class Fixture {

    public static final Card ACE = Card.of(Denomination.ACE, Suit.SPADE);
    public static final Card SIX = Card.of(Denomination.SIX, Suit.SPADE);
    public static final Card SEVEN = Card.of(Denomination.SEVEN, Suit.SPADE);
    public static final Card TEN = Card.of(Denomination.QUEEN, Suit.SPADE);
}
