package domain.gamer;

import domain.result.Score;

public class Dealer extends Gamer {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_HIT_CEILING = 17;
	private static final int DEALER_FIRST_OPENED_COUNT = 1;

	public Dealer() {
		super(DEALER_NAME);
	}

	@Override
	protected Score getHitPoint() {
		return Score.from(DEALER_HIT_CEILING);
	}

	@Override
	protected int firstOpenedCardsCount() {
		return DEALER_FIRST_OPENED_COUNT;
	}
}