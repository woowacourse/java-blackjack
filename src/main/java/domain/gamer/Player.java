package domain.gamer;

import domain.result.Score;

public class Player extends Gamer {
	private final Money money;
	private static final int PLAYER_HIT_CEILING = 21;

	public Player(Name name, Money money) {
		super(name);
		this.money = money;
	}

	@Override
	public Score getHitPoint() {
		return Score.of(PLAYER_HIT_CEILING);
	}

	public Money getMoney() {
		return money;
	}
}
