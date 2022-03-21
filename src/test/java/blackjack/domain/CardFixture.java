package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;

public class CardFixture {

    public static final Card SPADE_ACE = Card.of(CardNumber.ACE, Suit.SPADE);
    public static final Card SPADE_TWO = Card.of(CardNumber.TWO, Suit.SPADE);
    public static final Card SPADE_THREE = Card.of(CardNumber.THREE, Suit.SPADE);
    public static final Card SPADE_FOUR = Card.of(CardNumber.FOUR, Suit.SPADE);
    public static final Card SPADE_NINE = Card.of(CardNumber.NINE, Suit.SPADE);
    public static final Card SPADE_TEN = Card.of(CardNumber.TEN, Suit.SPADE);
}
