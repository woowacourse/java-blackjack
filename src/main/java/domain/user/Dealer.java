package domain.user;

public class Dealer extends User {
	private static final int MAXIMUM_DRAWABLE_SCORE = 16;

	public Dealer() {
	}

	public boolean isDrawable() {
		return cards.calculateScore() <= MAXIMUM_DRAWABLE_SCORE;
	}
}
