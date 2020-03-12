package blackjack.domain.user;

public class Player extends User {
	private static final int PLAYER_DRAWABLE_MAX_SCORE = 22;

	public Player(String name) {
		super(name);
	}

	@Override
	boolean canDraw() {
		return hand.calculateScore().isLowerThan(PLAYER_DRAWABLE_MAX_SCORE);
	}
}
