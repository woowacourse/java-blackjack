package domain.gamer;

import domain.result.Score;

public class Player extends Gamer {
	private static final int PLAYER_HIT_CEILING = 21;

	public Player(String name) {
		super(name);
	}

	@Override
	public Score getHitPoint() {
		return Score.from(PLAYER_HIT_CEILING);
	}
}
