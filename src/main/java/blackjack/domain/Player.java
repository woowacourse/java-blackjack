package blackjack.domain;

public class Player extends Gamer {

	public Player(Name name) {
		super(name);
	}

	@Override
	protected boolean isWinBySpecialCase(Gamer gamer) {
		if (this.isBlackJack() && !gamer.isBlackJack()) {
			return true;
		}
		return gamer.isBust() && !this.isBust();
	}

	@Override
	public boolean canHit() {
		return !this.isBlackJack() && !this.isBust();
	}
}
