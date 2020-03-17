package domain;

public class Player extends User {
	public Player(String name) {
		super(name);
	}

	@Override
	public boolean isWin(User that) {
		return isNotBust() && (that.isBust() || isScoreGreaterThan(that.getScore()));
	}
}
