package domain.participant;

import domain.result.Score;

public class Player extends Participant {
	private static final int PLAYER_HIT_CEILING = 21;

	public Player(String name) {
		super(name);
	}

	@Override
	public Score getHitPoint() {
		return Score.of(PLAYER_HIT_CEILING);
	}
}
