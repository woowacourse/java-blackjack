package domain;

public class Dealer extends User {

	private static final int UNDER_OVER_SCORE = 17;

	public Dealer(final String name) {
		this.name = name;
	}

	@Override
	public boolean isHittable() {
		return cards.isUnder(UNDER_OVER_SCORE);
	}
}
