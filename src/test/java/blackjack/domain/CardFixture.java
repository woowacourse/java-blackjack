package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;

public class CardFixture {
    public static final Card HEART_FOUR = new Card(Shape.HEART, Symbol.FOUR);
    public static final Card CLOVER_FIVE = new Card(Shape.CLOVER, Symbol.FIVE);
    public static final Card SPADE_SIX = new Card(Shape.SPADE, Symbol.SIX);
    public static final Card HEART_SEVEN = new Card(Shape.HEART, Symbol.SEVEN);
    public static final Card HEART_EIGHT = new Card(Shape.HEART, Symbol.EIGHT);
    public static final Card CLOVER_KING = new Card(Shape.CLOVER, Symbol.KING);
    public static final Card DIAMOND_JACK = new Card(Shape.DIAMOND, Symbol.JACK);
    public static final Card CLOVER_ACE = new Card(Shape.CLOVER, Symbol.ACE);
    public static final Card HEART_ACE = new Card(Shape.HEART, Symbol.ACE);
    public static final Card SPADE_ACE = new Card(Shape.SPADE, Symbol.ACE);
}
