package domain.participant;

import domain.result.Score;

public class Dealer extends Participant {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_HIT_POINT = 16;

	public Dealer() {
		super(DEALER_NAME);
	}

	@Override
	public int getHitPoint() {
		return DEALER_HIT_POINT;
	}
}