package blackjack.domain;

import java.util.function.Supplier;

public class Dealer extends Role {

	private final Supplier<Boolean> drawable;

	public Dealer(String name, Hand hand, Supplier<Boolean> drawable) {
		super(name, hand);
		this.drawable = drawable;
	}

	@Override
	public boolean canDraw() {
		if (hand.calculateOptimalScore() >= 21) {
			return false;
		}
		if (hand.calculateOptimalScore() < 17) {
			return true;
		}
		if (!hand.hasAce()) {
			return false;
		}
		return drawable.get();
	}
}
