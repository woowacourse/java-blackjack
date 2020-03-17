package domain;

public class Player extends User {
	public Player(String name) {
		super(name);
	}

	@Override
	public boolean isWin(User that) {
		boolean isScoreWin = isNotBust() && (that.isBust() || isScoreGreaterThan(that.getScore()));
		boolean isBlackJackWin = isBlackJack() && that.isNotBlackJack();
		return isScoreWin || isBlackJackWin;
	}
}
