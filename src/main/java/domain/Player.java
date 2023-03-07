package domain;

public class Player extends User {

	private static final int BLACK_JACK_SCORE = 21;

	public Player(final String name) {
		super(name);
	}

	@Override
	public boolean isHittable() {
		return cards.isUnder(BLACK_JACK_SCORE);
	}
}
