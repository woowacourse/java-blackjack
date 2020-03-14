package domain.gamer;

public class Player extends Gamer {
	private static final int PLAYER_HIT_CEILING = 21;
	private static final int PLAYER_FIRST_OPENED_COUNT = 2;

	public Player(String name) {
		super(name);
	}

	@Override
	protected int getHitPoint() {
		return PLAYER_HIT_CEILING;
	}

	@Override
	protected int firstOpenedCardsCount() {
		return PLAYER_FIRST_OPENED_COUNT;
	}
}
