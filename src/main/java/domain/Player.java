package domain;

public class Player extends User {
	public Player(String name) {
		super(name);
	}

	public boolean isWin(User that) {
		return isNotBust() && (that.isBust() || getScore() >= that.getScore());
	}
}
