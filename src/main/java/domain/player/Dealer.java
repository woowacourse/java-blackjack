package domain.player;

public class Dealer extends Player {

	private static final int MIN_SCORE_THRESHOLD = 16;
	private static final String DEALER_NAME = "딜러";

	public Dealer() {
		super(DEALER_NAME, 0);
	}

	@Override
	public boolean canReceiveCard() {
		return super.getScore() <= MIN_SCORE_THRESHOLD;
	}
}
