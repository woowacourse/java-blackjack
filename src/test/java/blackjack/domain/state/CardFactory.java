package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Symbol;

public class CardFactory {
    public static final Card SPADE_ACE = Card.of(Symbol.SPADE, Number.ACE);
    public static final Card SPADE_TWO = Card.of(Symbol.SPADE, Number.TWO);
    public static final Card SPADE_TEN = Card.of(Symbol.SPADE, Number.TEN);
    public static final Card SPADE_JACK = Card.of(Symbol.SPADE, Number.JACK);

    public static final Card HEART_ACE = Card.of(Symbol.HEART, Number.ACE);
    public static final Card HEART_TWO = Card.of(Symbol.HEART, Number.TWO);
    public static final Card HEART_TEN = Card.of(Symbol.HEART, Number.TEN);
    public static final Card HEART_JACK = Card.of(Symbol.HEART, Number.JACK);
}
