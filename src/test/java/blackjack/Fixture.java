package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;

public class Fixture {
    public static final Card SPADE_ACE = Card.of(Denomination.ACE, Symbol.SPADE);
    public static final Card SPADE_TWO = Card.of(Denomination.TWO, Symbol.SPADE);
    public static final Card SPADE_JACK = Card.of(Denomination.JACK, Symbol.SPADE);
}
