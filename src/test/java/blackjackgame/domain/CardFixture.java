package blackjackgame.domain;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Symbol;

public class CardFixture {
    public static final Card CLOVER_ACE = Card.of(Symbol.CLOVER, CardValue.ACE);
    public static final Card HEART_ACE = Card.of(Symbol.HEART, CardValue.ACE);
    public static final Card DIAMOND_ACE = Card.of(Symbol.DIAMOND, CardValue.ACE);
    public static final Card SPADE_ACE = Card.of(Symbol.SPADE, CardValue.ACE);
    public static final Card CLOVER_TWO = Card.of(Symbol.CLOVER, CardValue.TWO);
    public static final Card CLOVER_THREE = Card.of(Symbol.CLOVER, CardValue.THREE);
    public static final Card CLOVER_FOUR = Card.of(Symbol.CLOVER, CardValue.FOUR);
    public static final Card CLOVER_FIVE = Card.of(Symbol.CLOVER, CardValue.FIVE);
    public static final Card CLOVER_NINE = Card.of(Symbol.CLOVER, CardValue.NINE);
    public static final Card CLOVER_KING = Card.of(Symbol.CLOVER, CardValue.KING);
}
