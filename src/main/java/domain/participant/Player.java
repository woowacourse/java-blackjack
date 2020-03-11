package domain.participant;

import domain.result.Score;

public class Player extends Participant {
	public static final int PLAYER_HIT_POINT = 20;

	public Player(String name) {
		super(name);
	}

	@Override
	public int getHitPoint() {
		return PLAYER_HIT_POINT;
	}
}
