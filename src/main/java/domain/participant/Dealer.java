package domain.participant;

import domain.result.Score;

public class Dealer extends Participant {
	private static final String DEALER_NAME = "딜러";
	private static final int SIXTEEN = 16;

	public Dealer() {
		super(DEALER_NAME);
	}

	@Override
	public boolean canReceiveMore() {
		return Score.calculate(super.getCards()) <= SIXTEEN;
	}
}