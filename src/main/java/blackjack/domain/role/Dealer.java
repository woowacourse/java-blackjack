package blackjack.domain.role;

import java.util.function.Supplier;

import blackjack.domain.card.Hand;
import blackjack.service.BlackJackService;

public class Dealer extends Role {

	public static final int CAN_NOT_DRAW_STANDARD = 17;
	public static final int CAN_DRAW_STANDARD = 16;

	private static final String DEALER_NAME = "딜러";

	private final Supplier<Boolean> drawable;

	public Dealer(Hand hand, Supplier<Boolean> drawable) {
		super(DEALER_NAME, hand);
		this.drawable = drawable;
	}

	@Override
	public boolean canDraw() {
		if (hand.calculateOptimalScore() >= BlackJackService.OPTIMIZED_WINNING_NUMBER) {
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
}
