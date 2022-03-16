package blackjack.domain;

public class Player extends Gamer {

	public Player(Cards cards, Name name) {
		super(cards, name);
	}

	public Player(Name name) {
		super(new Cards(), name);
	}

	public Player() {
		super(new Cards(), new Name());
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
