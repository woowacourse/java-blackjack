package blackjack.domain;

import java.util.List;
import java.util.function.Supplier;

public class Dealer extends Role {

	public static final int CAN_NOT_DRAW_STANDARD = 17;
	public static final int CAN_DRAW_STANDARD = 16;

	private static final int openCard = 0;
	private static final String DEALER_NAME = "딜러";

	private final Supplier<Boolean> drawable;

	public Dealer(Hand hand, Supplier<Boolean> drawable) {
		super(DEALER_NAME, hand);
		this.drawable = drawable;
	}

	@Override
	public boolean canDraw() {
		if (hand.calculateOptimalScore() >= Hand.OPTIMIZED_WINNING_NUMBER) {
			return false;
		}
		if (hand.calculateOptimalScore() <= CAN_DRAW_STANDARD) {
			return true;
		}
		if (!hand.hasAce()) {
			return false;
		}
		return drawable.get();
	}

	@Override
	public List<Card> openHand() {
		List<Card> cards = hand.getCards();
		return List.of(cards.get(openCard));
	}
}
