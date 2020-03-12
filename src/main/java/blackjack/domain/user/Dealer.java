package blackjack.domain.user;

public class Dealer extends User {
	private static final int DEALER_DRAWABLE_MAX_SCORE = 17;

	public Dealer(String name) {
		super(name);
	}

	@Override
	boolean canDraw() {
		return hand.canDrawBy(DEALER_DRAWABLE_MAX_SCORE);
	}
}
