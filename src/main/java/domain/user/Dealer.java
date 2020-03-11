package domain.user;

public class Dealer extends User {
	private static final int MAXIMUM_DRAWABLE_SCORE = 16;

	private String name;

	public Dealer() {
		this.name = "딜러";
	}

	public boolean isDrawable() {
		return cards.calculateScore() <= MAXIMUM_DRAWABLE_SCORE;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
