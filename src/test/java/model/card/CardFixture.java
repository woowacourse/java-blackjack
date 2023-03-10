package model.card;

import static model.card.Shape.CLOVER;
import static model.card.Shape.DIAMOND;
import static model.card.Shape.HEART;
import static model.card.Shape.SPADE;
import static model.card.Value.ACE;
import static model.card.Value.FIVE;
import static model.card.Value.JACK;
import static model.card.Value.KING;
import static model.card.Value.NINE;
import static model.card.Value.SEVEN;
import static model.card.Value.SIX;
import static model.card.Value.TEN;
import static model.card.Value.THREE;
import static model.card.Value.TWO;

public class CardFixture {

    public static final Card HEART_ACE = new Card(HEART, ACE);
    public static final Card CLOVER_ACE = new Card(CLOVER, ACE);
    public static final Card SPADE_ACE = new Card(SPADE, ACE);
    public static final Card DIAMOND_ACE = new Card(DIAMOND, ACE);
    public static final Card DIAMOND_TWO = new Card(DIAMOND, TWO);
    public static final Card DIAMOND_THREE = new Card(DIAMOND, THREE);
    public static final Card DIAMOND_FIVE = new Card(DIAMOND, FIVE);
    public static final Card DIAMOND_SIX = new Card(DIAMOND, SIX);
    public static final Card DIAMOND_SEVEN = new Card(DIAMOND, SEVEN);
    public static final Card DIAMOND_NINE = new Card(DIAMOND, NINE);
    public static final Card DIAMOND_TEN = new Card(DIAMOND, TEN);
    public static final Card DIAMOND_JACK = new Card(DIAMOND, JACK);
    public static final Card DIAMOND_KING = new Card(DIAMOND, KING);
}
