package domain.gamer;

import domain.result.Score;

public class Player extends Gamer {
	private static final int PLAYER_HIT_CEILING = 21;

	public Player(Name name) {
		super(name);
	}

	@Override
	public Score getHitPoint() {
		return Score.of(PLAYER_HIT_CEILING);
	}
}
