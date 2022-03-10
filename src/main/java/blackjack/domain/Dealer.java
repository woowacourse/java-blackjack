package blackjack.domain;

import java.util.function.Supplier;

public class Dealer extends Role {

	public static final int CAN_NOT_DRAW_STANDARD = 17;
	public static final int CAN_DRAW_STANDARD = 16;

	private final Supplier<Boolean> drawable;

	public Dealer(Hand hand, Supplier<Boolean> drawable) {
		super("딜러", hand);
		this.drawable = drawable;
	}

	@Override
	public boolean canDraw() {
		if (hand.calculateOptimalScore() >= 21) {
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
