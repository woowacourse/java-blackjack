package domain.participant;

import domain.result.Score;

public class Dealer extends Participant {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_HIT_CEILING = 17;

	public Dealer() {
		super(DEALER_NAME);
	}

	@Override
	public Score getHitPoint() {
		return Score.of(DEALER_HIT_CEILING);
	}
}