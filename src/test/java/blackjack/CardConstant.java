package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

public class CardConstant {

	public static final Card CLOVER_ACE = Card.getInstance(CardShape.CLOVER, CardNumber.ACE);
	public static final Card CLOVER_TWO = Card.getInstance(CardShape.CLOVER, CardNumber.TWO);
	public static final Card CLOVER_THREE = Card.getInstance(CardShape.DIAMOND, CardNumber.THREE);
	public static final Card CLOVER_FIVE = Card.getInstance(CardShape.CLOVER, CardNumber.FIVE);
	public static final Card CLOVER_SEVEN = Card.getInstance(CardShape.SPADE, CardNumber.SEVEN);
	public static final Card CLOVER_EIGHT = Card.getInstance(CardShape.SPADE, CardNumber.EIGHT);
	public static final Card CLOVER_NINE = Card.getInstance(CardShape.SPADE, CardNumber.NINE);
	public static final Card CLOVER_TEN = Card.getInstance(CardShape.CLOVER, CardNumber.TEN);
}
