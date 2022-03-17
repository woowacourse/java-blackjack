package blackjack.domain.gamer;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;

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
	protected boolean isWinByBust(Gamer gamer) {
		return gamer.isBust() && !this.isBust();
	}

	@Override
	public boolean canHit() {
		return !this.isBlackJack() && !this.isBust();
	}
}
