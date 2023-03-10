package domain;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;

public class Fixtures {
    public static final Card ACE_CLOVER = new Card(Value.ACE, Shape.CLOVER);
    public static final Card TEN_CLOVER = new Card(Value.TEN, Shape.CLOVER);
    public static final Card TEN_HEART = new Card(Value.TEN, Shape.HEART);
    public static final Card TEN_SPADE = new Card(Value.TEN, Shape.SPADE);
}
