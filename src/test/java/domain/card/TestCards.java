package domain.card;

public class TestCards {
    public static final Card ACE_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.ACE);
    public static final Card TWO_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.TWO);
    public static final Card FIVE_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.FIVE);
    public static final Card SIX_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.SIX);
    public static final Card SEVEN_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.SEVEN);
    public static final Card EIGHT_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.EIGHT);
    public static final Card NINE_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.NINE);
    public static final Card JACK_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.JACK);
    public static final Card QUEEN_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.QUEEN);
    public static final Card KING_HEART = Card.getInstanceFromPool(CardType.HEART, CardName.KING);
    public static final Card JACK_SPADE = Card.getInstanceFromPool(CardType.SPADE, CardName.JACK);
    public static final Card SIX_DIAMOND = Card.getInstanceFromPool(CardType.DIAMOND, CardName.SIX);
}
