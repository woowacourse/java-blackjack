package domain.participant;

import domain.result.Score;

public class Player extends Participant {
	public static final int TWENTY_ONE = 21;

	public Player(String name) {
		super(name);
	}

	@Override
	public boolean canReceiveMore() {
		return Score.calculate(super.getCards()) < TWENTY_ONE;
	}
}
