package blackjack.domain;

public class Player extends Gamer {

	public Player(Name name) {
		super(name);
	}

	@Override
	public boolean canAddCards() {
		return !this.isBlackJack() && !this.isBust();
	}
}
